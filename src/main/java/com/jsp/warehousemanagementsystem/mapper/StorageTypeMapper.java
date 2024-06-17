package com.jsp.warehousemanagementsystem.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehousemanagementsystem.entity.StorageType;
import com.jsp.warehousemanagementsystem.requestdtos.StorageTypeRequest;
import com.jsp.warehousemanagementsystem.responsedtos.StorageTypeResponse;

@Component
public class StorageTypeMapper {

	public StorageType mapStorageTypeRequestToStorageType(StorageTypeRequest storageTypeRequest,StorageType storageType){
		storageType.setBreadthInMeters(storageTypeRequest.getBreadthInMeters());
		storageType.setCapacityInWeight(storageTypeRequest.getCapacityInWeight());
		storageType.setHeightInMeters(storageTypeRequest.getHeightInMeters());
		storageType.setLengthInMeters(storageTypeRequest.getLengthInMeters());
		return storageType;
	}

	public StorageTypeResponse mapStorageTypeToStorageTypeResponse(StorageType storageType) {
		return StorageTypeResponse.builder()
				.storageTypeId(storageType.getStorageTypeId())
				.breadthInMeters(storageType.getBreadthInMeters())
				.capacityInWeight(storageType.getCapacityInWeight())
				.heightInMeters(storageType.getHeightInMeters())
				.lengthInMeters(storageType.getLengthInMeters())
				.unitsAvailable(storageType.getUnitsAvailable())
				.build();
	}
}
