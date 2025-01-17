package com.jsp.warehousemanagementsystem.responsedtos;

import java.time.LocalDate;
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
public class InventoryResponse {

	private long productId;
	private String productTitle;
	private double lengthInMeters;
	private double breadthInMeters;
	private double heightInMeters;
	private double weightInKg;
	@Enumerated(EnumType.STRING)
	private	List<MaterialType> materialTypes; 
	private LocalDate restockedAt;
	private long sellerId;
}
