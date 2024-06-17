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

import com.jsp.warehousemanagementsystem.requestdtos.ClientRequest;
import com.jsp.warehousemanagementsystem.responsedtos.ApiKeyResponse;
import com.jsp.warehousemanagementsystem.responsedtos.ClientResponse;
import com.jsp.warehousemanagementsystem.service.ClientService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
@Autowired
private ClientService clientService;

@PostMapping("/clients")
public ResponseEntity<ResponseStructure<ApiKeyResponse>> addClient(@RequestBody @Valid ClientRequest clientRequest){
	return clientService.addClient(clientRequest);
}

@PutMapping("/clients/{clientId}")
public ResponseEntity<ResponseStructure<ClientResponse>> updateClient(@RequestBody ClientRequest clientRequest,
		@PathVariable long clientId){
	return clientService.updateClient(clientRequest,clientId);
}
}
