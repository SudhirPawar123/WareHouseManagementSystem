package com.jsp.warehousemanagementsystem.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.jsp.warehousemanagementsystem.entity.Storage;
import com.jsp.warehousemanagementsystem.requestdtos.StorageRequest;
import com.jsp.warehousemanagementsystem.responsedtos.StorageResponse;

import jakarta.validation.Valid;

@Component
public class StorageMapper {

	public Storage mapStorageRequestToStorage(StorageRequest storageRequest, Storage storage) {
		storage.setBlockName(storageRequest.getBlockName());
		storage.setSection(storageRequest.getSection());
		storage.setMaterialTypes(storageRequest.getMaterialTypes());
		return storage;
	}



	public StorageResponse mapStorageToStorageResponse(Storage storage) {

		return StorageResponse.builder()
				.storageId(storage.getStorageId())
				.section(storage.getSection())
				.blockName(storage.getBlockName())
				.maxAdditionalWeightInKg(storage.getMaxAdditionalWeightInKg())
				.availableArea(storage.getAvailableAreaInMeters())
				.materialTypes(storage.getMaterialTypes())
				.build();
	}

}
