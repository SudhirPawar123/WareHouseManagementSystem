package com.jsp.warehousemanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehousemanagementsystem.requestdtos.AddressRequest;
import com.jsp.warehousemanagementsystem.responsedtos.AddressResponse;
import com.jsp.warehousemanagementsystem.responsedtos.AddressResponse;
import com.jsp.warehousemanagementsystem.service.AddressService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PreAuthorize("hasAuthority('CREATE_ADDRESS')")
    @PostMapping("/warehouses/{wareHouseId}/addresses")
    public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(
            @RequestBody @Valid AddressRequest addressRequest,
            @PathVariable @Valid int wareHouseId) {
        return addressService.addAddress(addressRequest, wareHouseId);
    }

    @PreAuthorize("hasAuthority('UPDATE_ADDRESS')")
    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(
            @RequestBody @Valid AddressRequest addressRequest,
            @PathVariable @Valid Long addressId) {
        return addressService.updateAddress(addressRequest, addressId);
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<ResponseStructure<AddressResponse>> findAddress(@PathVariable @Valid Long addressId) {
        return addressService.findAddress(addressId);
    }

    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/addresses")
    public ResponseEntity<ResponseStructure<List<AddressResponse>>> addresses() {
        return addressService.addresses();
    }

}