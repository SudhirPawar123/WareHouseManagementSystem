package com.jsp.warehousemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehousemanagementsystem.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{

}
