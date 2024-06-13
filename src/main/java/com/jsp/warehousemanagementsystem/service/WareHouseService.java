package com.jsp.warehousemanagementsystem.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagementsystem.requestdtos.WareHouseRequest;
import com.jsp.warehousemanagementsystem.responsedtos.WareHouseResponse;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

import jakarta.validation.Valid;

public interface WareHouseService {

	ResponseEntity<ResponseStructure<WareHouseResponse>> createWareHouse(WareHouseRequest wareHouseRequest);

	ResponseEntity<ResponseStructure<WareHouseResponse>> updateWareHouse(WareHouseRequest wareHouseRequest,
			int wareHouseId);

	ResponseEntity<ResponseStructure<WareHouseResponse>> findWareHouse(@Valid int wareHouseId);

	ResponseEntity<ResponseStructure<List<WareHouseResponse>>> findWareHouses();

}
