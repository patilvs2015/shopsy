package com.recommendation.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@OpenAPIDefinition
@EnableFeignClients
@EnableJpaRepositories
public class ProductRecommendationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductRecommendationServiceApplication.class, args);
	}
	@Bean
	public OpenAPI baseOpenAPI() {
		
		return new OpenAPI()
				.info(new Info()
				.title("spring doc")
				.version("3.0.0")      //varsion depend user
				.description("spring doc"));
	}
}
