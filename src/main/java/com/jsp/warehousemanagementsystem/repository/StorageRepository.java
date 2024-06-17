package com.jsp.warehousemanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehousemanagementsystem.entity.Storage;

public interface StorageRepository extends JpaRepository<Storage, Long> {
	
}
