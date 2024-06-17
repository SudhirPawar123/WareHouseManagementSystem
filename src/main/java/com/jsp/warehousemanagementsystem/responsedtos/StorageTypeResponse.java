package com.jsp.warehousemanagementsystem.responsedtos;

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
public class StorageTypeResponse {
	private long storageTypeId;
	private	double capacityInWeight;
	private	double lengthInMeters;
	private	double  breadthInMeters;
	private	double  heightInMeters;
	private long unitsAvailable;
}
