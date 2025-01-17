package com.jsp.warehousemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehousemanagementsystem.requestdtos.InventoryRequest;
import com.jsp.warehousemanagementsystem.responsedtos.InventoryResponse;
import com.jsp.warehousemanagementsystem.service.InventoryService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {
	@Autowired
	private InventoryService inventoryService;

	
	 @PostMapping("/clients/{clientId}/storages/{storageId}/inventories")
	 public ResponseEntity<ResponseStructure<InventoryResponse> > addInventory(
			@RequestBody InventoryRequest inventoryRequest
			,@PathVariable long clientId
			,@PathVariable long storageId
			,@RequestParam("quantity") long quantity){
		 return inventoryService.addInventory(inventoryRequest,clientId,storageId,quantity);
	 }
	
	 
	    @GetMapping("/inventories/{inventoryId}")
	    public ResponseEntity<ResponseStructure<InventoryResponse>> findInventory(
	             @PathVariable Long inventoryId) {
	        return inventoryService.findInventory(inventoryId);
	    }
	    
	    @GetMapping("/inventories")
	    public ResponseEntity<ResponseStructure<List<InventoryResponse>>> findInventories(){
	        return inventoryService.findInventories();
	    }

}
