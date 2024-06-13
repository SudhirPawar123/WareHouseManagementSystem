package com.jsp.warehousemanagementsystem.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsp.warehousemanagementsystem.entity.Address;
import com.jsp.warehousemanagementsystem.requestdtos.AddressRequest;
import com.jsp.warehousemanagementsystem.responsedtos.AddressResponse;

@Component
public class AddressMapper {
	@Autowired
	private WareHouseMapper wareHouseMapper;
	
	public Address mapAddressRequestToAddress(AddressRequest addressRequest, Address address){
	    address.setAddressLine(addressRequest.getAddressLine());
	    address.setCity(addressRequest.getCity());
	    address.setState(addressRequest.getState());
	    address.setCountry(addressRequest.getCountry());
	    address.setPincode(addressRequest.getPincode());
	    address.setLongitude(addressRequest.getLongitude());
	    address.setLatitude(addressRequest.getLatitude());
	    return  address;
	}
	public AddressResponse mapAddressToAddressResponse(Address address){
	    return AddressResponse.builder()
	            .addressId(address.getAddressId())
	            .addressLine(address.getAddressLine())
	            .city(address.getCity())
	            .state(address.getState())
	            .country(address.getCountry())
	            .pincode(address.getPincode())
	            .longitude(address.getLongitude())
	            .latitude(address.getLatitude())
	            .build();
	}
}

