package com.jsp.warehousemanagementsystem.entity;


import jakarta.persistence.Entity;
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
public class Batch {
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
private long batchId;
private long batchQuantity;

@ManyToOne
private Storage storage;

@ManyToOne
private Inventory inventory;
}
