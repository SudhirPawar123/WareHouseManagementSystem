package com.jsp.warehousemanagementsystem.entity;

import java.util.List;

import com.jsp.warehousemanagementsystem.enums.AdminType;
import com.jsp.warehousemanagementsystem.enums.Privilege;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Admin {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int adminId;
private String name;
private String email;
private String password;
@Enumerated(EnumType.STRING)
private AdminType adminType;
}
