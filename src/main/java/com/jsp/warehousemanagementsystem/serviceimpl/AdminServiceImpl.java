package com.jsp.warehousemanagementsystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagementsystem.entity.Admin;
import com.jsp.warehousemanagementsystem.enums.AdminType;
import com.jsp.warehousemanagementsystem.enums.Privilege;
import com.jsp.warehousemanagementsystem.exception.IllegalOperationException;
import com.jsp.warehousemanagementsystem.mapper.AdminMapper;
import com.jsp.warehousemanagementsystem.repository.AdminRepository;
import com.jsp.warehousemanagementsystem.requestdtos.AdminRequest;
import com.jsp.warehousemanagementsystem.responsedtos.AdminResponse;
import com.jsp.warehousemanagementsystem.service.AdminService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private AdminMapper adminMapper;

	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> addAdmin( AdminRequest adminRequest) {
		if(adminRepository.existsByAdminType(AdminType.SUPER_ADMIN))
			throw new IllegalOperationException("Super Admin is Present ");
		
		
		Admin admin = adminMapper.mapAdminRequestToAdmin(adminRequest, new Admin());
		admin.setAdminType(AdminType.SUPER_ADMIN);
//		admin.setPrivileges(List.of(Privilege.CREATE_ADMIN,Privilege.CREATE_WAREHOUSE));
		admin = adminRepository.save(admin);
		AdminResponse adminResponse = adminMapper.mapAdminToAdminResponse(admin);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<AdminResponse>()
				.setStatus(HttpStatus.CREATED.value()).setMessage("Admin created").setData(adminResponse));
	}
}
