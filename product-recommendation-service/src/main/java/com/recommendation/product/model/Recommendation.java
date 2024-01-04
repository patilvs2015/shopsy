package com.recommendation.product.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "recommendation")
public class Recommendation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "rating")
	private int rating;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
    private Product product;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	public Recommendation(int rating, Product product, User user) {
		this.rating = rating;
		this.product = product;
		this.user = user;
	}
}
