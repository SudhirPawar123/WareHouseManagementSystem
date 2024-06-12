package com.jsp.warehousemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.warehousemanagementsystem.entity.WareHouse;
import com.jsp.warehousemanagementsystem.requestdtos.AdminRequest;
import com.jsp.warehousemanagementsystem.responsedtos.AdminResponse;
import com.jsp.warehousemanagementsystem.service.AdminService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
	
	@Autowired
	public AdminService adminService;
	
    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<AdminResponse>> addSuperAdmin(@RequestBody @Valid AdminRequest adminRequest){
   	return adminService.addSuperAdmin(adminRequest);
    }
 
    @PostMapping("/warehouses/{warehouseId}/admins")
    public ResponseEntity<ResponseStructure<AdminResponse>>  createAdmin(
    		@RequestBody @Valid  AdminRequest adminRequest, @PathVariable("warehouseId") int wareHouseId ){
    	return adminService.createAdmin(adminRequest,wareHouseId);
    }
    
	
    @PutMapping("/admins")
    public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(@RequestBody AdminRequest adminRequest){
    	return adminService.updateAdmin(adminRequest);
    }
}
