package com.jsp.warehousemanagementsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsp.warehousemanagementsystem.entity.Admin;
import com.jsp.warehousemanagementsystem.entity.WareHouse;
import com.jsp.warehousemanagementsystem.enums.AdminType;
import com.jsp.warehousemanagementsystem.responsedtos.AdminResponse;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	boolean existsByAdminType(AdminType superAdmin);

	Optional<Admin> findByEmail(String username);

	List<Admin> findAllByAdminType(AdminType admin);


}
