package com.jsp.warehousemanagementsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagementsystem.requestdtos.InventoryRequest;
import com.jsp.warehousemanagementsystem.responsedtos.InventoryResponse;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

public interface InventoryService {

	ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(Long inventoryId);

	ResponseEntity<ResponseStructure<List<InventoryResponse>>> findInventories();

	ResponseEntity<ResponseStructure<InventoryResponse>> addInventory(InventoryRequest inventoryRequest, long clientId,
			long storageId, long quantity);

}
