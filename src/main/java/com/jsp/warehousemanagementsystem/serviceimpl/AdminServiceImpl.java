package com.jsp.warehousemanagementsystem.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagementsystem.entity.Admin;
import com.jsp.warehousemanagementsystem.entity.WareHouse;
import com.jsp.warehousemanagementsystem.enums.AdminType;
import com.jsp.warehousemanagementsystem.enums.Privilege;
import com.jsp.warehousemanagementsystem.exception.AdminNotFoundException;
import com.jsp.warehousemanagementsystem.exception.EmailAlreadyExistException;
import com.jsp.warehousemanagementsystem.exception.IllegalOperationException;
import com.jsp.warehousemanagementsystem.exception.WareHouseNotFoundByIdException;
import com.jsp.warehousemanagementsystem.mapper.AdminMapper;
import com.jsp.warehousemanagementsystem.repository.AdminRepository;
import com.jsp.warehousemanagementsystem.repository.WareHouseRepository;
import com.jsp.warehousemanagementsystem.requestdtos.AdminRequest;
import com.jsp.warehousemanagementsystem.responsedtos.AdminResponse;
import com.jsp.warehousemanagementsystem.service.AdminService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private WareHouseRepository wareHouseRepository;

	//Adding super admin
	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> addSuperAdmin(AdminRequest adminRequest) {
		if (adminRepository.existsByAdminType(AdminType.SUPER_ADMIN))
			throw new IllegalOperationException("Super Admin is Present ");

		Admin admin = adminMapper.mapAdminRequestToAdmin(adminRequest, new Admin());
		admin.setAdminType(AdminType.SUPER_ADMIN);
		// admin.setPrivileges(List.of(Privilege.CREATE_ADMIN,Privilege.CREATE_WAREHOUSE));
		admin = adminRepository.save(admin);
		AdminResponse adminResponse = adminMapper.mapAdminToAdminResponse(admin);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseStructure<AdminResponse>()
				.setStatus(HttpStatus.CREATED.value()).setMessage("Admin created").setData(adminResponse));
	}

	//Creating Admin
	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> createAdmin(AdminRequest adminRequest,
			int wareHouseId) {
		return wareHouseRepository.findById(wareHouseId).map(wareHouseCheck -> {
			Optional<Admin> emailValidation=adminRepository.findByEmail(adminRequest.getEmail());
			if(emailValidation.isEmpty()) {
			Admin admin = adminMapper.mapAdminRequestToAdmin(adminRequest, new Admin());
			admin.setAdminType(AdminType.ADMIN);
			adminRepository.save(admin);
			wareHouseCheck.setAdmin(admin);
			wareHouseRepository.save(wareHouseCheck);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new ResponseStructure<AdminResponse>().setStatus(HttpStatus.CREATED.value())
							.setMessage("Admin created").setData(adminMapper.mapAdminToAdminResponse(admin)));
			}else {
				throw new EmailAlreadyExistException("duplicate email are not allowed");
			}
		}).orElseThrow(() -> new WareHouseNotFoundByIdException("WareHouse is not exist"));

	}


//	Updating Admin
	@Override
	public ResponseEntity<ResponseStructure<AdminResponse>> updateAdmin(AdminRequest adminRequest) {
		String email=SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getName();

		return adminRepository.findByEmail(email).map(admin -> {
		Admin	admin1 = adminMapper.mapAdminRequestToAdmin(adminRequest, new Admin());
		admin1.setAdminId(admin.getAdminId());
		admin1.setAdminType(AdminType.ADMIN);

			Admin updatedAdmin=adminRepository.save(admin1);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseStructure<AdminResponse>()
							.setStatus(HttpStatus.OK.value())
							.setMessage("Admin updated")
							.setData(adminMapper.mapAdminToAdminResponse(updatedAdmin)));
		}).orElseThrow(() -> new AdminNotFoundException("admin is not available to update"));
	}
	
	
	

}
