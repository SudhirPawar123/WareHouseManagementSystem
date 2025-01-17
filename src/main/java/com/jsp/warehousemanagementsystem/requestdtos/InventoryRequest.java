package com.jsp.warehousemanagementsystem.requestdtos;

import java.time.LocalDate;
import java.util.List;

import com.jsp.warehousemanagementsystem.enums.MaterialType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequest {
	private String productTitle;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double weightInKg;
	@Enumerated(EnumType.STRING)
	private	List<MaterialType> materialTypes; 
	private long sellerId;
}
