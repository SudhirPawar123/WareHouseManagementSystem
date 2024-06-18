package com.jsp.warehousemanagementsystem.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagementsystem.entity.Storage;
import com.jsp.warehousemanagementsystem.entity.StorageType;
import com.jsp.warehousemanagementsystem.entity.WareHouse;
import com.jsp.warehousemanagementsystem.excep.StorageTypeNotExistException;
import com.jsp.warehousemanagementsystem.exception.StorageNotFoundByIdException;
import com.jsp.warehousemanagementsystem.exception.WareHouseNotFoundByIdException;
import com.jsp.warehousemanagementsystem.mapper.StorageMapper;
import com.jsp.warehousemanagementsystem.repository.StorageRepository;
import com.jsp.warehousemanagementsystem.repository.StorageTypeRepository;
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

	@Autowired
	private StorageTypeRepository  storageTypeRepository;



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

	@Override
	public ResponseEntity<ResponseStructure<String>> addStorage(@Valid StorageRequest storageRequest,
			@Valid int wareHouseId, @Valid Long storageTypeId, int noOfStorageUnits) {
		WareHouse wareHouse = wareHouseRepository.findById(wareHouseId).orElseThrow(() ->
		new WareHouseNotFoundByIdException("WareHouse Id : " + wareHouseId + ", is not exist"));

		StorageType storageType = storageTypeRepository.findById(storageTypeId).orElseThrow(()->
		new StorageTypeNotExistException("StorageTypeId : "+storageTypeId+", StorageType is not exist"));

		storageType.setUnitsAvailable(storageType.getUnitsAvailable()+noOfStorageUnits);
		storageType = storageTypeRepository.save(storageType);


		double totalCapacity = storageType.getCapacityInWeight() * noOfStorageUnits + wareHouse.getTotalCapacityInKg();
		wareHouse.setTotalCapacityInKg(totalCapacity);
		wareHouseRepository.save(wareHouse);

		List<Storage> storages = new ArrayList<Storage>();
		while (noOfStorageUnits > 0) {
			Storage storage = storageMapper.mapStorageRequestToStorage(storageRequest, new Storage());
			storage.setWareHouse(wareHouse);
			storage.setStorageType(storageType);
			storage.setMaxAdditionalWeightInKg(storageType.getCapacityInWeight());
			storage.setAvailableAreaInMeters(storageType.getHeightInMeters()*storageType.getBreadthInMeters()*storageType.getLengthInMeters());

			storages.add(storage);
			noOfStorageUnits--;
		}

		storages = storageRepository.saveAll(storages);

		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<String>()
				.setStatus(HttpStatus.CREATED.value())
				.setMessage("Storage Created")
				.setData(storages.size() + " storages are created"));
	}

	@Override
	public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(StorageRequest storageRequest,
			long storageId) {
		return storageRepository.findById(storageId).map(storage -> {
			Storage storage1 = storageMapper.mapStorageRequestToStorage(storageRequest, storage);

			storage = storageRepository.save(storage1);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<StorageResponse>()
					.setStatus(HttpStatus.OK.value())
					.setMessage("Storage Updated")
					.setData(storageMapper.mapStorageToStorageResponse(storage)));
		}).orElseThrow(() -> new StorageNotFoundByIdException("StorageId : " + storageId + ", is not exist"));
	}





}
