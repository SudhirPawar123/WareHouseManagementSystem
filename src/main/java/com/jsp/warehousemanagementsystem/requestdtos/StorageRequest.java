package com.jsp.warehousemanagementsystem.requestdtos;

import java.util.List;

import com.jsp.warehousemanagementsystem.enums.MaterialType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class StorageRequest {


//	@NotNull(message = "BlockName cannot be null")
//	@NotBlank(message = "BlockName cannot be blank")
//	@Pattern(regexp = "^[a-zA-Z]+$", message = "BlockName must contain only letters.")
	private String blockName;

//	@NotNull(message = "Section cannot be null")
//	@NotBlank(message = "Section cannot be blank")
//	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Section must contain both letters and numbers.")
	private String section;


//	@NotNull(message = "MaterialTypes cannot be null")
//	@NotBlank(message = "MaterialTypes cannot be blank")
	private	List<MaterialType> materialTypes; 

}
