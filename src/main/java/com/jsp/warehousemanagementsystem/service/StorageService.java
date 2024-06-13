package com.jsp.warehousemanagementsystem.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagementsystem.requestdtos.StorageRequest;
import com.jsp.warehousemanagementsystem.responsedtos.StorageResponse;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;
import com.jsp.warehousemanagementsystem.util.SimpleResponseStructure;

import jakarta.validation.Valid;

public interface StorageService {

	ResponseEntity<SimpleResponseStructure<String>> addStorage(@Valid StorageRequest storageRequest, int wareHouseId,
			int noOfStorageUnits);

	ResponseEntity<ResponseStructure<StorageResponse>> updateStorage(long storageId, StorageRequest storageRequest);


}
