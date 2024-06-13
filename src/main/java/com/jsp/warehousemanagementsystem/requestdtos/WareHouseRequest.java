package com.jsp.warehousemanagementsystem.requestdtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WareHouseRequest {
	@NotNull(message ="warehousename cannot be null")
	@NotBlank(message ="warehousename cannot be blank")
	@Pattern(regexp = "^[a-zA-Z]+$")
	private String name;
	
	private Double totalCapacityInKg;


}
