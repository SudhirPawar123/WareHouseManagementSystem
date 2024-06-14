package com.jsp.warehousemanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehousemanagementsystem.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {


	List<Address> findByCity(String city);
}