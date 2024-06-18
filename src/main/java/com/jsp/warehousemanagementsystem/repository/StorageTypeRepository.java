package com.jsp.warehousemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehousemanagementsystem.entity.StorageType;

public interface StorageTypeRepository extends JpaRepository<StorageType, Long>{

	boolean existsByLengthInMetersAndBreadthInMetersAndHeightInMetersAndCapacityInWeight(double lengthInMeters,
			double breadthInMeters, double heightInMeters, double capacityInWeight);

}
