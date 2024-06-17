package com.jsp.warehousemanagementsystem.service;

import org.springframework.http.ResponseEntity;

import com.jsp.warehousemanagementsystem.requestdtos.ClientRequest;
import com.jsp.warehousemanagementsystem.responsedtos.ApiKeyResponse;
import com.jsp.warehousemanagementsystem.responsedtos.ClientResponse;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

import jakarta.validation.Valid;

public interface ClientService {

	ResponseEntity<ResponseStructure<ApiKeyResponse>> addClient(@Valid ClientRequest clientRequest);

	ResponseEntity<ResponseStructure<ClientResponse>> updateClient(ClientRequest clientRequest, long clientId);

}
