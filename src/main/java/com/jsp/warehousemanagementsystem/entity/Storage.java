package com.jsp.warehousemanagementsystem.entity;

import java.util.List;

import com.jsp.warehousemanagementsystem.enums.MaterialType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Storage {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Id
private long storageId;
private String blockName;
private String section;
private	Double capacityInWeight;
private	Double lengthInMeters;
private	Double  breadthInMeters;
private	Double  heightInMeters;
@Enumerated(EnumType.STRING)
private	List<MaterialType> materialTypes; 
private	Double maxAdditionalWeightInKg;
private	Double availableAreaInMeters;


@ManyToOne
private WareHouse wareHouse;
}
