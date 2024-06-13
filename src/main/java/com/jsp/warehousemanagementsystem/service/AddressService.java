package com.jsp.warehousemanagementsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagementsystem.requestdtos.AddressRequest;
import com.jsp.warehousemanagementsystem.responsedtos.AddressResponse;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

import jakarta.validation.Valid;

import com.jsp.warehousemanagementsystem.util.ResponseStructure;

public interface AddressService {

    ResponseEntity<ResponseStructure<AddressResponse>> addAddress(@Valid AddressRequest addressRequest,@Valid int wareHouseId);

    ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(@Valid AddressRequest addressRequest,@Valid Long addressId);

    ResponseEntity<ResponseStructure<AddressResponse>> findAddress(@Valid Long addressId);

    ResponseEntity<ResponseStructure<List<AddressResponse>>> addresses();
}