package com.jsp.warehousemanagementsystem.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagementsystem.entity.WareHouse;
import com.jsp.warehousemanagementsystem.requestdtos.AdminRequest;
import com.jsp.warehousemanagementsystem.responsedtos.AdminResponse;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

import jakarta.validation.Valid;

public interface AdminService {


	ResponseEntity<ResponseStructure<AdminResponse>> addSuperAdmin(AdminRequest adminRequest);

	ResponseEntity<ResponseStructure<AdminResponse>> createAdmin( AdminRequest adminRequest,int wareHouseId);

	ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest);

}
