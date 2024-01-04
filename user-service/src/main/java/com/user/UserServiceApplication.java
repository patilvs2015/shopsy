package com.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.user.repository.UserRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@OpenAPIDefinition
public class UserServiceApplication {

	/*
	 * @Autowired private UserRepository userRepository;
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);

	}

	@Bean
	public OpenAPI baseOpenAPI() {

		return new OpenAPI().info(new Info().title("spring doc").version("3.0.0") // varsion depend user
				.description("spring doc"));
	}

}
