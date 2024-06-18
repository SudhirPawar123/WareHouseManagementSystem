package com.jsp.warehousemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehousemanagementsystem.requestdtos.StorageTypeRequest;
import com.jsp.warehousemanagementsystem.responsedtos.StorageTypeResponse;
import com.jsp.warehousemanagementsystem.service.StorageTypeService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class StorageTypeController {

	@Autowired
	private StorageTypeService storageTypeService;
	

	
	  @PostMapping("/storageTypes")
	    public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(
	            @RequestBody StorageTypeRequest storageTypeRequest){

	      return storageTypeService.createStorageType(storageTypeRequest);
	    }
	  
	   @PutMapping("/storageTypes/{storageTypeId}")
	    public ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(
	            @RequestBody StorageTypeRequest storageTypeRequest,
	            @PathVariable Long storageTypeId){
	        return storageTypeService.updateStorageType(storageTypeRequest, storageTypeId);
	    }
}
