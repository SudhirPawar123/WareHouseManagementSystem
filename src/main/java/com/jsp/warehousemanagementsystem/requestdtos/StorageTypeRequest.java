package com.jsp.warehousemanagementsystem.requestdtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StorageTypeRequest {
	private	double capacityInWeight;
	private	double lengthInMeters;
	private	double  breadthInMeters;
	private	double  heightInMeters;
}
