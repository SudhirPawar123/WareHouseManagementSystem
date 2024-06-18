package com.jsp.warehousemanagementsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagementsystem.requestdtos.StorageRequest;
import com.jsp.warehousemanagementsystem.responsedtos.StorageResponse;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;
import com.jsp.warehousemanagementsystem.util.SimpleResponseStructure;

import jakarta.validation.Valid;

public interface StorageService {


	ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(StorageRequest storageRequest,long storageId );


	ResponseEntity<ResponseStructure<StorageResponse>> findStorage(@Valid long storageId);

	ResponseEntity<ResponseStructure<List<StorageResponse>>> findStorages();

	ResponseEntity<ResponseStructure<String>> addStorage(@Valid StorageRequest storageRequest, @Valid int wareHouseId,
			@Valid Long storageTypeId, int noOfStorageUnits);




}
