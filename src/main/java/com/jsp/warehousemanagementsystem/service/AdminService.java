package com.jsp.warehousemanagementsystem.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagementsystem.requestdtos.AdminRequest;
import com.jsp.warehousemanagementsystem.responsedtos.AdminResponse;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

public interface AdminService {


	ResponseEntity<ResponseStructure<AdminResponse>> addAdmin(AdminRequest adminRequest);

}
