package com.product.entity;

import java.math.BigDecimal;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table (name = "products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
	
	    @Id
	    @GeneratedValue (strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column (name = "product_name")
	    @NotNull
	    private String productName;

	    @Column (name = "price")
	    @NotNull
	    private BigDecimal price;

	    @Column (name = "discription")
	    private String discription;

	    @Column (name = "category")
	    @NotNull
	    private String category;

	    @Column (name = "availability")
	    @NotNull
	    private int availability;

	
	
	
	
	
	
	
	
	
	
	
	

}
