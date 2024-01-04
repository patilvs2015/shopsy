package com.recommendation.product.service;

import java.util.List;

import com.recommendation.product.model.Recommendation;

public interface RecommendationService {
	Recommendation getRecommendationById(Long recommendationId);

	Recommendation saveRecommendation(Recommendation recommendation);

	List<Recommendation> getAllRecommendationByProductName(String productName);

	void deleteRecommendation(Long id);
	
	
	
	//List<Recommendation> getAllRecommendationByUserName(String userName);
}
