package com.jsp.warehousemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehousemanagementsystem.requestdtos.PurchaseOrderRequest;
import com.jsp.warehousemanagementsystem.responsedtos.PurchaseOrderResponse;
import com.jsp.warehousemanagementsystem.service.PurchaseOrderService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

@RestController
@RequestMapping("/api/v1")
public class PurchaseOrderController {
	@Autowired
	private PurchaseOrderService purchaseOrderService;

	@PostMapping("/PurchaseOrder")
	public ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(
			@RequestBody PurchaseOrderRequest purchaseOrderRequest){
		return purchaseOrderService.createPurchaseOrder(purchaseOrderRequest);
	}
}
