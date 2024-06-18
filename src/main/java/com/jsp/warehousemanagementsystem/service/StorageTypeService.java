package com.jsp.warehousemanagementsystem.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagementsystem.requestdtos.StorageTypeRequest;
import com.jsp.warehousemanagementsystem.responsedtos.StorageTypeResponse;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

public interface StorageTypeService {


	

	ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(StorageTypeRequest storageTypeRequest);

	ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(StorageTypeRequest storageTypeRequest,
			Long storageTypeId);

}
