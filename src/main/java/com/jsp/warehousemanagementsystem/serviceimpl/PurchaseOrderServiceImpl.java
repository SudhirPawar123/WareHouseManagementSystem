package com.jsp.warehousemanagementsystem.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagementsystem.entity.PurchaseOrder;
import com.jsp.warehousemanagementsystem.mapper.PurchaseOrderMapper;
import com.jsp.warehousemanagementsystem.repository.PurchaseOrderRepository;
import com.jsp.warehousemanagementsystem.requestdtos.PurchaseOrderRequest;
import com.jsp.warehousemanagementsystem.responsedtos.PurchaseOrderResponse;
import com.jsp.warehousemanagementsystem.service.PurchaseOrderService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{
	@Autowired
	private PurchaseOrderMapper purchaseOrderMapper;
	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	@Override
	public ResponseEntity<ResponseStructure<PurchaseOrderResponse>> createPurchaseOrder(
			PurchaseOrderRequest purchaseOrderRequest) {
		PurchaseOrder purchaseOrder= purchaseOrderMapper.mapPurchaseOrderRequestToPurchaseOrder(
				purchaseOrderRequest,new PurchaseOrder());
		purchaseOrderRepository.save(purchaseOrder);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseStructure<PurchaseOrderResponse>()
				.setStatus(HttpStatus.CREATED.value())
				.setMessage("PurchaseOrder is created")
				.setData(purchaseOrderMapper.mapPurchaseOrderToPurchaseOrderResponse(purchaseOrder)));
	}

}
