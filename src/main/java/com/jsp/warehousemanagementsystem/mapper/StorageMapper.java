package com.jsp.warehousemanagementsystem.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jsp.warehousemanagementsystem.entity.Storage;
import com.jsp.warehousemanagementsystem.requestdtos.StorageRequest;
import com.jsp.warehousemanagementsystem.responsedtos.StorageResponse;

import jakarta.validation.Valid;

@Component
public class StorageMapper {

	public Storage mapStorageRequestToStorage(@Valid StorageRequest storageRequest, Storage storage) {
		storage.setBlockName(storageRequest.getBlockName());
		storage.setSection(storageRequest.getSection());
		storage.setCapacityInWeight(storageRequest.getCapacityInWeight());
		storage.setLengthInMeters(storageRequest.getLengthInMeters());
		storage.setHeightInMeters(storageRequest.getHeightInMeters());
		storage.setBreadthInMeters(storageRequest.getBreadthInMeters());
		storage.setMaterialTypes(storageRequest.getMaterialTypes());
		return storage;
	}



	public StorageResponse mapStorageToStorageResponse(Storage storage) {

		return StorageResponse.builder()
				.storageId(storage.getStorageId())
				.section(storage.getSection())
				.blockName(storage.getBlockName())
				.capacityInWeight(storage.getCapacityInWeight())
				.maxAdditionalWeightInKg(storage.getMaxAdditionalWeightInKg())
				.availableArea(storage.getAvailableAreaInMeters())
				.materialTypes(storage.getMaterialTypes())
				.build();
	}

}
