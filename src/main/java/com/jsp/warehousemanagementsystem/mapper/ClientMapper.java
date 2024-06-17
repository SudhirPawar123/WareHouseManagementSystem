package com.jsp.warehousemanagementsystem.mapper;

import org.springframework.stereotype.Component;

import com.jsp.warehousemanagementsystem.entity.Client;
import com.jsp.warehousemanagementsystem.requestdtos.ClientRequest;
import com.jsp.warehousemanagementsystem.responsedtos.ApiKeyResponse;
import com.jsp.warehousemanagementsystem.responsedtos.ClientResponse;


@Component
public class ClientMapper {

	public Client mapClientRequestToClient(ClientRequest clientRequest, Client client) {
		client.setBusinessName(clientRequest.getBusinessName());
		client.setContactNumber(clientRequest.getContactNumber());
		client.setEmail(clientRequest.getEmail());
		return client;
	}

	public ClientResponse mapClientToClientResponse(Client client) {
		return ClientResponse.builder()
				.clientId(client.getClientId())
				.businessName(client.getBusinessName())
				.contactNumber(client.getContactNumber())
				.email(client.getEmail())
				.build();
	}

	public ApiKeyResponse mapClientToApiKeyResponse(Client client) {
		return ApiKeyResponse.builder().apiKey(client.getApiKey()).build();
	}

}
