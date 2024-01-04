package com.order.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "items")
public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;

	@Column(name = "quantity")
	@NotNull
	private int quantity;

	@Column(name = "subtotal")
	@NotNull
	private BigDecimal subTotal;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Product product;

	@ManyToMany(mappedBy = "items")
	@JsonIgnore
	private List<Order> orders;

	public Item() {

	}

	public Item(@NotNull int quantity,BigDecimal subTotal, Product product) {
		this.quantity = quantity;
		this.product = product;
		this.subTotal = subTotal;
	}

}
