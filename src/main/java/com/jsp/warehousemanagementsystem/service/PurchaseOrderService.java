package com.jsp.warehousemanagementsystem.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagementsystem.requestdtos.PurchaseOrderRequest;
import com.jsp.warehousemanagementsystem.responsedtos.PurchaseOrderResponse;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

public interface PurchaseOrderService {

	ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(
			PurchaseOrderRequest purchaseOrderRequest);

}
