package com.jsp.warehousemanagementsystem.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehousemanagementsystem.entity.Admin;
import com.jsp.warehousemanagementsystem.requestdtos.AdminRequest;
import com.jsp.warehousemanagementsystem.responsedtos.AdminResponse;

@Component
public class AdminMapper {

	public AdminResponse mapAdminToAdminResponse(Admin admin) {
		return AdminResponse.builder().adminId(admin.getAdminId())
				.name(admin.getName())
				.email(admin.getEmail())
				.build();

	}

	public Admin mapAdminRequestToAdmin(AdminRequest adminRequest, Admin admin) {
		admin.setName(adminRequest.getName());
		admin.setEmail(adminRequest.getEmail());
		admin.setPassword(adminRequest.getPassword());
		return admin;
	}
}
