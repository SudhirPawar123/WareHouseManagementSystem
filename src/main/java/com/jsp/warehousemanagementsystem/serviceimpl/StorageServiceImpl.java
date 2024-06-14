package com.jsp.warehousemanagementsystem.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagementsystem.entity.Storage;
import com.jsp.warehousemanagementsystem.entity.WareHouse;
import com.jsp.warehousemanagementsystem.exception.StorageNotFoundByIdException;
import com.jsp.warehousemanagementsystem.exception.WareHouseNotFoundByIdException;
import com.jsp.warehousemanagementsystem.mapper.StorageMapper;
import com.jsp.warehousemanagementsystem.repository.StorageRepository;
import com.jsp.warehousemanagementsystem.repository.WareHouseRepository;
import com.jsp.warehousemanagementsystem.requestdtos.StorageRequest;
import com.jsp.warehousemanagementsystem.responsedtos.StorageResponse;
import com.jsp.warehousemanagementsystem.service.StorageService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;
import com.jsp.warehousemanagementsystem.util.SimpleResponseStructure;

import jakarta.validation.Valid;

@Service
public class StorageServiceImpl implements StorageService{

	@Autowired
	private StorageMapper storageMapper;

	@Autowired
	private StorageRepository storageRepository;

	@Autowired
	private WareHouseRepository wareHouseRepository;

	@Override
	public ResponseEntity<SimpleResponseStructure<String>> addStorage(@Valid StorageRequest storageRequest,
			int wareHouseId, int noOfStorageUnits) {
		WareHouse wareHouse=wareHouseRepository.findById(wareHouseId)
				.orElseThrow(()->new WareHouseNotFoundByIdException("warehouse is not found by id"));

		double totalcapacity=storageRequest.getCapacityInWeight()
				*noOfStorageUnits+wareHouse.getTotalCapacityInKg();

		List<Storage> storages=new ArrayList<>();
		int storageUnits=noOfStorageUnits;
		while(noOfStorageUnits>0) {
			Storage storage=storageMapper.mapStorageRequestToStorage(storageRequest, new Storage());
			storage.setWareHouse(wareHouse);
			storage.setAvailableAreaInMeters(storageRequest.getBreadthInMeters()
					*storageRequest.getHeightInMeters()
					*storageRequest.getLengthInMeters());
			storage.setMaxAdditionalWeightInKg(storageRequest.getCapacityInWeight());
			storages.add(storage);
			noOfStorageUnits--;
       }

		wareHouse.setTotalCapacityInKg(totalcapacity);
		wareHouseRepository.save(wareHouse);
		storageRepository.saveAll(storages);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new SimpleResponseStructure<String>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage(" "+storageUnits+" Storage Created"));
	}

	@Override
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(StorageRequest storageRequest,long storageId) {
		return storageRepository.findById(storageId).map(existingStorage->{
			
		double capacityInWeight=existingStorage.getCapacityInWeight();

			Storage storage=storageMapper.mapStorageRequestToStorage(storageRequest,existingStorage);
			storage.setStorageId(storageId);
			storage.setMaxAdditionalWeightInKg(storage.getCapacityInWeight());
			storage.setAvailableAreaInMeters(storage.getBreadthInMeters()
					*storage.getHeightInMeters()
					*storage.getLengthInMeters());
			
			WareHouse wareHouse = existingStorage.getWareHouse();
	
			double totalCapacity = storageRequest.getCapacityInWeight() + wareHouse.getTotalCapacityInKg() - capacityInWeight;
			wareHouse.setTotalCapacityInKg(totalCapacity);
System.out.println(totalCapacity);
			wareHouseRepository.save(wareHouse);
			storage.setWareHouse(wareHouse);

			storage=storageRepository.save(storage);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<StorageResponse>()
					.setStatus(HttpStatus.OK.value())
					.setMessage("Storage Successfully Updated")
					.setData(storageMapper.mapStorageToStorageResponse(storage)));	

		}).orElseThrow(()-> new StorageNotFoundByIdException("No storage found by the given id"));
}

	@Override
	public ResponseEntity<ResponseStructure<StorageResponse>> findStorage(@Valid long storageId) {
		return storageRepository.findById(storageId).map(storage -> {
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<StorageResponse>()
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("Storage Founded")
				.setData(storageMapper.mapStorageToStorageResponse(storage)));
		}).orElseThrow(() -> new StorageNotFoundByIdException("No storage found by the given id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<StorageResponse>>> findStorages() {
		List<StorageResponse> listStorages=storageRepository.findAll()
				.stream()
				.map(storageMapper::mapStorageToStorageResponse)
				.toList();
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(new ResponseStructure<List<StorageResponse>>()
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("List of storages founded")
				.setData(listStorages));
	}


}
