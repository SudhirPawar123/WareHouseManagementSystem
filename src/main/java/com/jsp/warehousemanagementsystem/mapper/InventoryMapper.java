package com.jsp.warehousemanagementsystem.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehousemanagementsystem.entity.Inventory;
import com.jsp.warehousemanagementsystem.requestdtos.InventoryRequest;
import com.jsp.warehousemanagementsystem.responsedtos.InventoryResponse;

@Component
public class InventoryMapper {

	public Inventory mapInventoryRequestToInventory(InventoryRequest inventoryRequest, Inventory inventory) {
		inventory.setBreadthInMeters(inventoryRequest.getBreadthInMeters());
		inventory.setHeightInMeters(inventoryRequest.getHeightInMeters());
		inventory.setLengthInMeters(inventoryRequest.getLengthInMeters());
		inventory.setMaterialTypes(inventoryRequest.getMaterialTypes());
		inventory.setProductTitle(inventoryRequest.getProductTitle());
		inventory.setWeightInKg(inventoryRequest.getWeightInKg());
		inventory.setSellerId(inventoryRequest.getSellerId());
		return inventory;		
	}

	public InventoryResponse mapInventoryToInventoryResponse(Inventory inventory) {
		return InventoryResponse.builder()
				.productId(inventory.getProductId())
				.productTitle(inventory.getProductTitle())
				.weightInKg(inventory.getWeightInKg())
				.materialTypes(inventory.getMaterialTypes())
				.restockedAt(inventory.getRestockedAt())
				.sellerId(inventory.getSellerId())
				.build();
	}

}
