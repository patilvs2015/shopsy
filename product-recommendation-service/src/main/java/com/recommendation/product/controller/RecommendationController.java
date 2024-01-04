package com.recommendation.product.controller;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recommendation.product.feignclient.ProductClient;
import com.recommendation.product.feignclient.UserClient;
import com.recommendation.product.httpheader.HeaderGenerator;
import com.recommendation.product.model.Product;
import com.recommendation.product.model.Recommendation;
import com.recommendation.product.model.User;
import com.recommendation.product.service.RecommendationService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

	private static final Logger logger = LoggerFactory.getLogger(RecommendationController.class);
	@Autowired
	private RecommendationService recommendationService;

	@Autowired
	private ProductClient productClient;

	@Autowired
	private UserClient userClient;

	@Autowired
	private HeaderGenerator headerGenerator;

	@GetMapping("/getAllrecommendationsByProductName/{name}")
	private ResponseEntity<List<Recommendation>> getAllRating(@PathVariable("name") String productName) {
		List<Recommendation> recommendations = recommendationService.getAllRecommendationByProductName(productName);
		if (!recommendations.isEmpty()) {
			return new ResponseEntity<List<Recommendation>>(recommendations,
					headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
		}
		return new ResponseEntity<List<Recommendation>>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/{userId}/saveRecommendations/{productId}")
	private ResponseEntity<Recommendation> saveRecommendations(@PathVariable("userId") Long userId,
			@PathVariable("productId") Long productId, @RequestParam("rating") int rating, HttpServletRequest request) {

		// Product product = (Product) productClient.findProductNameById(productId);
		List<Object[]> ProductNameById = productClient.findProductNameById(productId);

		// productClient.
		logger.info("{} ", ProductNameById);

		List<Product> product1 = new ArrayList<>();

		ProductNameById.forEach(objects1 -> {

			Product product = new Product();

			product.setProductName((String) objects1[0]);

			product1.add(product);

		});

		// User user = userClient.getUserById(userId);
		List<Object[]> userNameById = userClient.getUserNameById(userId);

		List<User> user = new ArrayList<>();

		userNameById.forEach(objects -> {

			User user1 = new User();

			user1.setUserName((String) objects[0]);

			user.add(user1);
		});

		if (ProductNameById != null && userNameById != null) {
			try {
				Recommendation recommendation = new Recommendation();
				recommendation.setRating(rating);

				for (Product pro : product1) {
					recommendation.setProduct(pro);
				}

				for (User user2 : user) {
					recommendation.setUser(user2);
				}

				// recommendation.setUser(user);

				recommendationService.saveRecommendation(recommendation);
				return new ResponseEntity<Recommendation>(recommendation,
						headerGenerator.getHeadersForSuccessPostMethod(request, recommendation.getId()),
						HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Recommendation>(headerGenerator.getHeadersForError(),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<Recommendation>(headerGenerator.getHeadersForError(), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/recommendations/{id}")
	private ResponseEntity<Void> deleteRecommendations(@PathVariable("id") Long id) {
		Recommendation recommendation = recommendationService.getRecommendationById(id);
		if (recommendation != null) {
			try {
				recommendationService.deleteRecommendation(id);
				return new ResponseEntity<Void>(headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Void>(headerGenerator.getHeadersForError(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<Void>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}
	
	/*
	 * @GetMapping("/getAllrecommendationsByUserName/{userName}") private
	 * ResponseEntity<List<Recommendation>>
	 * getAllRatingByUserName(@PathVariable("userName") String userName) {
	 * List<Recommendation> recommendations =
	 * recommendationService.getAllRecommendationByUserName(userName); if
	 * (!recommendations.isEmpty()) { return new
	 * ResponseEntity<List<Recommendation>>(recommendations,
	 * headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK); } return new
	 * ResponseEntity<List<Recommendation>>(headerGenerator.getHeadersForError(),
	 * HttpStatus.NOT_FOUND); }
	 */
	
	
	
	
	
	

}
