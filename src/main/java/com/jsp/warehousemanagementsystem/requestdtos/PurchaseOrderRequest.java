package com.jsp.warehousemanagementsystem.requestdtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PurchaseOrderRequest {
	
	private long orderQuantity;
	private String invoiceLink;
	private long customerId;
}
