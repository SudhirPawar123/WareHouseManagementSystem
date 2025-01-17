package com.jsp.warehousemanagementsystem.serviceimpl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagementsystem.entity.Batch;
import com.jsp.warehousemanagementsystem.entity.Client;
import com.jsp.warehousemanagementsystem.entity.Inventory;
import com.jsp.warehousemanagementsystem.entity.Storage;
import com.jsp.warehousemanagementsystem.enums.MaterialType;
import com.jsp.warehousemanagementsystem.exception.ClientNotFoundByIdException;
import com.jsp.warehousemanagementsystem.exception.IllegalOperationException;
import com.jsp.warehousemanagementsystem.exception.InventoryNotExistException;
import com.jsp.warehousemanagementsystem.exception.StorageNotFoundByIdException;
import com.jsp.warehousemanagementsystem.mapper.InventoryMapper;
import com.jsp.warehousemanagementsystem.repository.ClientRepository;
import com.jsp.warehousemanagementsystem.repository.InventoryRepository;
import com.jsp.warehousemanagementsystem.repository.StorageRepository;
import com.jsp.warehousemanagementsystem.requestdtos.InventoryRequest;
import com.jsp.warehousemanagementsystem.responsedtos.InventoryResponse;
import com.jsp.warehousemanagementsystem.service.InventoryService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

@Service
public class InventoryServiceImpl implements InventoryService{

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private StorageRepository storageRepository;

	@Autowired
	private InventoryMapper inventoryMapper; 

	@Autowired
	private ClientRepository clientRepository;

//   @Autowired
//   private Batch batch;

	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> addInventory(InventoryRequest inventoryRequest,
			long clientId,long storageId, long quantity) {
		Storage storage=storageRepository.findById(storageId).orElseThrow(() -> new StorageNotFoundByIdException("storage is not found"));

		double productArea=inventoryRequest.getBreadthInMeters()*inventoryRequest.getHeightInMeters()*inventoryRequest.getLengthInMeters();
		
		if(storage.getAvailableAreaInMeters()>=productArea && storage.getMaxAdditionalWeightInKg()>=inventoryRequest.getWeightInKg())
		{
		Inventory inventory=inventoryMapper.mapInventoryRequestToInventory(inventoryRequest, new Inventory());
		
		if(storage.getAvailableAreaInMeters()>productArea)
		{
			storage.setAvailableAreaInMeters(storage.getAvailableAreaInMeters()-productArea);	
		}else
		{
			storage.setAvailableAreaInMeters(productArea-storage.getAvailableAreaInMeters());
		}

		if(storage.getMaxAdditionalWeightInKg()>inventory.getWeightInKg()) 
		{
			storage.setMaxAdditionalWeightInKg( storage.getMaxAdditionalWeightInKg() -inventory.getWeightInKg());
		}else 
		{
			storage.setMaxAdditionalWeightInKg(inventory.getWeightInKg()- storage.getMaxAdditionalWeightInKg());
		}
		storage.setStorageId(storageId);
		storageRepository.save(storage);
		Client client=clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundByIdException("Client is not available"));
		inventory.setClient(client);
		inventoryRepository.save(inventory);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<InventoryResponse>()
						.setStatus(HttpStatus.CREATED.value())
						.setMessage("Inventory created")
						.setData(inventoryMapper.mapInventoryToInventoryResponse(inventory)));
		}
		 return null;
	   }


	@Override
	public ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(Long inventoryId) {
		return inventoryRepository.findById(inventoryId).map(inventory -> {
			return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<InventoryResponse>()
					.setStatus(HttpStatus.FOUND.value())
					.setMessage("Inventory Founded")
					.setData(inventoryMapper.mapInventoryToInventoryResponse(inventory)));
		}).orElseThrow(() -> new InventoryNotExistException("InventoryId : " + inventoryId + ", is not exist"));
	}

	@Override
	public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findInventories() {
		List<InventoryResponse> inventoryResponses = inventoryRepository
				.findAll()
				.stream()
				.map(inventory -> inventoryMapper.mapInventoryToInventoryResponse(inventory))
				.toList();
		return ResponseEntity.status(HttpStatus.FOUND).body(new ResponseStructure<List<InventoryResponse>>()
				.setStatus(HttpStatus.FOUND.value())
				.setMessage("Inventories are Founded")
				.setData(inventoryResponses));
	}


}
