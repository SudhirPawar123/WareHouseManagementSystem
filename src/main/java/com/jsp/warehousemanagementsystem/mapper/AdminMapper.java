package com.jsp.warehousemanagementsystem.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jsp.warehousemanagementsystem.entity.Admin;
import com.jsp.warehousemanagementsystem.requestdtos.AdminRequest;
import com.jsp.warehousemanagementsystem.responsedtos.AdminResponse;

@Component
public class AdminMapper {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public AdminResponse mapAdminToAdminResponse(Admin admin) {
		return AdminResponse.builder().adminId(admin.getAdminId())
				.name(admin.getName())
				.email(admin.getEmail())
				.build();

	}
//
	public Admin mapAdminRequestToAdmin(AdminRequest adminRequest, Admin admin) {

		admin.setName(adminRequest.getName());
		admin.setEmail(adminRequest.getEmail());
		admin.setPassword(passwordEncoder.encode(adminRequest.getPassword()));
		return admin;
	}
}
