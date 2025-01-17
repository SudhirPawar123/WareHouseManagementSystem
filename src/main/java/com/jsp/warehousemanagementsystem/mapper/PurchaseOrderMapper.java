package com.jsp.warehousemanagementsystem.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehousemanagementsystem.entity.PurchaseOrder;
import com.jsp.warehousemanagementsystem.requestdtos.PurchaseOrderRequest;
import com.jsp.warehousemanagementsystem.responsedtos.PurchaseOrderResponse;

@Component
public class PurchaseOrderMapper {

	public PurchaseOrder mapPurchaseOrderRequestToPurchaseOrder(PurchaseOrderRequest purchaseOrderRequest,
			PurchaseOrder purchaseOrder) {
		purchaseOrder.setOrderQuantity(purchaseOrderRequest.getOrderQuantity());
		purchaseOrder.setInvoiceLink(purchaseOrderRequest.getInvoiceLink());
		purchaseOrder.setCustomerId(purchaseOrderRequest.getCustomerId());
		return purchaseOrder;
	}

	public PurchaseOrderResponse mapPurchaseOrderToPurchaseOrderResponse(PurchaseOrder purchaseOrder) {
		return PurchaseOrderResponse.builder()
				.orderId(purchaseOrder.getOrderId())
				.orderQuantity(purchaseOrder.getOrderQuantity())
				.invoiceLink(purchaseOrder.getInvoiceLink())
				.customerId(purchaseOrder.getCustomerId())
				.build();
	}

}
