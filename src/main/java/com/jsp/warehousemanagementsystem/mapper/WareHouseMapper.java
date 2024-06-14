package com.jsp.warehousemanagementsystem.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehousemanagementsystem.entity.WareHouse;
import com.jsp.warehousemanagementsystem.requestdtos.WareHouseRequest;
import com.jsp.warehousemanagementsystem.responsedtos.WareHouseResponse;

@Component
public class WareHouseMapper {

	public WareHouse mapWareHouseRequestToWareHouse(WareHouseRequest wareHouseRequest, WareHouse wareHouse) {
         wareHouse.setName(wareHouseRequest.getName());
		 return wareHouse;
	}

public WareHouseResponse mapWareHouseToWareHouseResponse(WareHouse wareHouse){
	return WareHouseResponse.builder()
			.wareHouseId(wareHouse.getWareHouseId())
			.name(wareHouse.getName())
			.totalCapacityInKg(wareHouse.getTotalCapacityInKg())
			.build();
	}
	
}
