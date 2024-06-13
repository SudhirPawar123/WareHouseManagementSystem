package com.jsp.warehousemanagementsystem.requestdtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class AddressRequest {
    @NotNull(message = "City can not be null")
    @NotBlank(message = "City can not be blank")
    private String addressLine;

    @NotNull(message = "City can not be null")
    @NotBlank(message = "City can not be blank")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "City must contain only letters.")
    private String city;

    @NotNull(message = "State can not be null")
    @NotBlank(message = "State can not be blank")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "State must contain only letters.")
    private String state;

    @NotNull(message = "Country can not be null")
    @NotBlank(message = "Country can not be blank")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Country must contain only letters.")
    private String country;

    @NotNull(message = "Pincode is mandatory")
//    @Pattern(regexp = "^[0-9]*$", message = "Pincode contains only number")
    private Integer pincode;

    @NotNull(message = "Longitude can not be null")
    @NotBlank(message = "Longitude can not be blank")
    private String longitude;

    @NotNull(message = "Latitude can not be null")
    @NotBlank(message = "Latitude can not be blank")
    private String latitude;
}