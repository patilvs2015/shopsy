package com.order.entity;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "orderDetsils")
public class OrderDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String userName;
	
	private LocalDate orderedDate;
	
	private String productName;
	
	private int quantity;
	
	private BigDecimal price;
	
	private BigDecimal subTotal;
	
	private BigDecimal total;
	
	
	

}
