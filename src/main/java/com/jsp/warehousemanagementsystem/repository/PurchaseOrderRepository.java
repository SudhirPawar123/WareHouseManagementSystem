package com.jsp.warehousemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehousemanagementsystem.entity.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

}
