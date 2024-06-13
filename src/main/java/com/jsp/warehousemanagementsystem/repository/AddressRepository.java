package com.jsp.warehousemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehousemanagementsystem.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}