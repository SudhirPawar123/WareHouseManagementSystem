package com.jsp.warehousemanagementsystem.filters;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jsp.warehousemanagementsystem.entity.Client;
import com.jsp.warehousemanagementsystem.exception.BadCredencialsException;
import com.jsp.warehousemanagementsystem.exception.ClientNotFoundByIdException;
import com.jsp.warehousemanagementsystem.exception.ClientNotFoundByNameException;
import com.jsp.warehousemanagementsystem.exception.IllegalOperationException;
import com.jsp.warehousemanagementsystem.exception.UsernameNotFoundException;
import com.jsp.warehousemanagementsystem.repository.ClientRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor

public class ApiKeyFilter extends OncePerRequestFilter {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getSession(false) != null) {
			throw new IllegalOperationException("Illegal operation");
		}

		if (!request.getRequestURI().equals("/api/v1/client/register")) {
			String apiKey = request.getHeader("API-KEY");
			String username = request.getHeader("USERNAME");

			if (apiKey != null || username != null) {
				Client client = clientRepository.findByEmail(username)
						.orElseThrow(() -> new ClientNotFoundByNameException("client not found by name"));
				if (!apiKey.equals(client.getApiKey()))
					throw new BadCredencialsException("Invalid Credentials");
			} else
				throw new UsernameNotFoundException("username not found exception");
		}
		filterChain.doFilter(request, response);

	}

}
