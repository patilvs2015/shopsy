package com.recommendation.product.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "product_name")
	private String productName;

	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private List<Recommendation> recomendations;

	public Product(String productName, List<Recommendation> recomendations) {
		this.productName = productName;
		this.recomendations = recomendations;
	}
}
