package com.jsp.warehousemanagementsystem.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagementsystem.entity.StorageType;
import com.jsp.warehousemanagementsystem.excep.StorageTypeNotExistException;
import com.jsp.warehousemanagementsystem.exception.StorageTypeAlreadyExistException;
import com.jsp.warehousemanagementsystem.mapper.StorageTypeMapper;
import com.jsp.warehousemanagementsystem.repository.StorageRepository;
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


	@Autowired
	private StorageRepository storageRepository;

	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> createStorageType(
			StorageTypeRequest storageTypeRequest) {
	    boolean bl = storageTypeRepository.existsByLengthInMetersAndBreadthInMetersAndHeightInMetersAndCapacityInWeight(
                storageTypeRequest.getLengthInMeters(),
                storageTypeRequest.getBreadthInMeters(),
                storageTypeRequest.getHeightInMeters(),
                storageTypeRequest.getCapacityInWeight());

        if(!bl) {
            StorageType storageType = storageTypeMapper.mapStorageTypeRequestToStorageType(storageTypeRequest, new StorageType());
            storageType = storageTypeRepository.save(storageType);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<StorageTypeResponse>()
                    .setStatus(HttpStatus.CREATED.value())
                    .setMessage("StorageType Created")
                    .setData(storageTypeMapper.mapStorageTypeToStorageTypeResponse(storageType)));
        }else
            throw new StorageTypeAlreadyExistException("StorageType is already exist in database");
	}

	@Override
	public ResponseEntity<ResponseStructure<StorageTypeResponse>> updateStorageType(
			StorageTypeRequest storageTypeRequest, Long storageTypeId) {
	     return storageTypeRepository.findById(storageTypeId).map(storageType->{
	         storageType = storageTypeMapper.mapStorageTypeRequestToStorageType(storageTypeRequest, storageType);


	             return ResponseEntity.status(HttpStatus.OK).body(new ResponseStructure<StorageTypeResponse>()
	                     .setStatus(HttpStatus.OK.value())
	                     .setMessage("StoreType is updated")
	                     .setData(storageTypeMapper.mapStorageTypeToStorageTypeResponse(storageType)));
	         }).orElseThrow(()->new StorageTypeNotExistException("StorageType does not exist by id"));
	     }
	

	
	
	


}
