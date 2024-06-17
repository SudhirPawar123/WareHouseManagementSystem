package com.jsp.warehousemanagementsystem.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagementsystem.entity.StorageType;
import com.jsp.warehousemanagementsystem.mapper.StorageTypeMapper;
import com.jsp.warehousemanagementsystem.repository.StorageTypeRepository;
import com.jsp.warehousemanagementsystem.requestdtos.StorageTypeRequest;
import com.jsp.warehousemanagementsystem.responsedtos.StorageTypeResponse;
import com.jsp.warehousemanagementsystem.service.StorageTypeService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

@Service
public class StorageTypeServiceImpl implements StorageTypeService {
	
	
	@Autowired
	private StorageTypeMapper storageTypeMapper;
	
	@Autowired
	private StorageTypeRepository storageTypeRepository;

	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(
			StorageTypeRequest storageTypeRequest, long storageId) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(
//			StorageTypeRequest storageTypeRequest) {
//	StorageType storageType=storageTypeMapper.mapStorageTypeRequestToStorageType(storageTypeRequest,new StorageType());
//	storageTypeRepository.save(storageType);
//		return ResponseEntity.status(HttpStatus.CREATED)
//				.body(new ResponseStructure<StorageTypeResponse>()
//				.setStatus(HttpStatus.CREATED.value())
//				.setMessage("Storagetype is created")
//				.setData(storageTypeMapper.mapStorageTypeToStorageTypeResponse(storageType)));
//	}

}
