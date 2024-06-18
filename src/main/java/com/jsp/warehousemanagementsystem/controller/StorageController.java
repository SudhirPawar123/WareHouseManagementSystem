package com.jsp.warehousemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehousemanagementsystem.requestdtos.StorageRequest;
import com.jsp.warehousemanagementsystem.responsedtos.StorageResponse;
import com.jsp.warehousemanagementsystem.responsedtos.WareHouseResponse;
import com.jsp.warehousemanagementsystem.service.StorageService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;
import com.jsp.warehousemanagementsystem.util.SimpleResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class StorageController {
	@Autowired
	private StorageService storageService;
	

	  @PostMapping("/warehouses/{wareHouseId}/storageTypes/{storageTypeId}/storages")
	    @PreAuthorize("hasAuthority('CREATE_STORAGE')")
	  public ResponseEntity<ResponseStructure<String>> addStorage(
	            @RequestBody @Valid StorageRequest storageRequest,
	            @PathVariable @Valid int wareHouseId,
	            @PathVariable @Valid Long storageTypeId,
	            @RequestParam("no_of_storage_units") int noOfStorageUnits){
	      return storageService.addStorage(storageRequest, wareHouseId, storageTypeId, noOfStorageUnits);
	    }
	
	  @PutMapping("/storages/{storageId}")
	    @PreAuthorize("hasAuthority('UPDATE_STORAGE')")
	  public ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(
	            @RequestBody @Valid StorageRequest storageRequest,
	            @PathVariable @Valid Long storageId){
	        return storageService.updateStorage(storageRequest, storageId);
	    }
	
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping("/storages/{storageId}")
	public ResponseEntity<ResponseStructure<StorageResponse>> findStorage(@PathVariable @Valid long storageId){
		return storageService.findStorage(storageId);
	}
	

	@PreAuthorize("hasAuthority('READ')")
	@GetMapping("/storages")
	public ResponseEntity<ResponseStructure<List<StorageResponse>>> findStorages(){
		return storageService.findStorages();
	}
	

}
