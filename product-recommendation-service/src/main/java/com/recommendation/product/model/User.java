package com.recommendation.product.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "user_name")
	private String userName;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Recommendation> recomendations;

	

	public User(String userName, List<Recommendation> recomendations) {
		this.userName = userName;
		this.recomendations = recomendations;
	}

}
