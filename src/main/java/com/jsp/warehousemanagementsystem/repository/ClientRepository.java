package com.jsp.warehousemanagementsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.warehousemanagementsystem.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

	Optional<Client> findByEmail(String username);
}
