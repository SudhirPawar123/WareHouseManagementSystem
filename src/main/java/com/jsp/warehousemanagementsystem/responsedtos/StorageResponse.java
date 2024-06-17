package com.jsp.warehousemanagementsystem.responsedtos;

import java.util.List;

import com.jsp.warehousemanagementsystem.enums.MaterialType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StorageResponse {
	private long storageId;
	private String blockName;
	private String section;
	private	Double maxAdditionalWeightInKg;
	private	Double availableArea;
	private List<MaterialType> materialTypes;
}
