package com.jsp.warehousemanagementsystem.responsedtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PurchaseOrderResponse {
	private long orderId;
	private long orderQuantity;
	private String invoiceLink;
	private long customerId;
}
