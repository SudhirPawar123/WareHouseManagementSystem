package com.jsp.warehousemanagementsystem.responsedtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AddressResponse {
    private Long addressId;
    private String addressLine;
    private String city;
    private String state;
    private String country;
    private Integer pincode;
    private String longitude;
    private String latitude;
}