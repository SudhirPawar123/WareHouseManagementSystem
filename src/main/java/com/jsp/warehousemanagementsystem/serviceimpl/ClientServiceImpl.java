package com.jsp.warehousemanagementsystem.serviceimpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.warehousemanagementsystem.entity.Client;
import com.jsp.warehousemanagementsystem.exception.ClientNotFoundByIdException;
import com.jsp.warehousemanagementsystem.mapper.ClientMapper;
import com.jsp.warehousemanagementsystem.repository.ClientRepository;
import com.jsp.warehousemanagementsystem.requestdtos.ClientRequest;
import com.jsp.warehousemanagementsystem.responsedtos.ApiKeyResponse;
import com.jsp.warehousemanagementsystem.responsedtos.ClientResponse;
import com.jsp.warehousemanagementsystem.service.ClientService;
import com.jsp.warehousemanagementsystem.util.ResponseStructure;

import jakarta.validation.Valid;

@Service
public class ClientServiceImpl implements ClientService{
@Autowired
private ClientRepository clientRepository;
@Autowired
private ClientMapper clientMapper;

@Override
public ResponseEntity<ResponseStructure<ApiKeyResponse>> addClient(@Valid ClientRequest clientRequest) {
	Client client=clientMapper.mapClientRequestToClient(clientRequest , new Client());
	client.setApiKey(UUID.randomUUID().toString());
	clientRepository.save(client);
	return ResponseEntity.status(HttpStatus.CREATED)
			.body(new ResponseStructure<ApiKeyResponse>()
			.setStatus(HttpStatus.CREATED.value())
			.setMessage("Client Created")
			.setData(clientMapper.mapClientToApiKeyResponse(client)));
}

@Override
public ResponseEntity<ResponseStructure<ClientResponse>> updateClient(ClientRequest clientRequest, long clientId) {
	return clientRepository.findById(clientId).map(client -> {
		Client client1=	clientMapper.mapClientRequestToClient(clientRequest, client);
		client1.setApiKey(client.getApiKey());
		client1.setClientId(clientId);
		clientRepository.save(client1);
		return ResponseEntity.status(HttpStatus.OK )
				.body(new ResponseStructure<ClientResponse>()
				.setStatus(HttpStatus.OK.value())
				.setMessage("Client updated")
				.setData(clientMapper.mapClientToClientResponse(client1)));

	}).orElseThrow(() -> new ClientNotFoundByIdException("Client is not available to update"));

}
}
