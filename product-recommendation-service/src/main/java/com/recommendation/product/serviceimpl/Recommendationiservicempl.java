package com.recommendation.product.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recommendation.product.model.Recommendation;
import com.recommendation.product.repository.RecommendationRepository;
import com.recommendation.product.service.RecommendationService;

@Service
public class Recommendationiservicempl implements RecommendationService {

	@Autowired
	private RecommendationRepository recommendationRepository;

	@Override
	public Recommendation getRecommendationById(Long recommendationId) {
		// TODO Auto-generated method stub
		return recommendationRepository.findById(recommendationId).get();
	}

	@Override
	public Recommendation saveRecommendation(Recommendation recommendation) {
		// TODO Auto-generated method stub
		return recommendationRepository.save(recommendation);
	}

	@Override
	public List<Recommendation> getAllRecommendationByProductName(String productName) {
		// TODO Auto-generated method stub
		return recommendationRepository.findAllRatingByProductName(productName);
	}

	@Override
	public void deleteRecommendation(Long id) {
		// TODO Auto-generated method stub
		recommendationRepository.deleteById(id);
	}

	/*
	 * @Override public List<Recommendation> getAllRecommendationByUserName(String
	 * userName) { // TODO Auto-generated method stub return
	 * recommendationRepository.findAllRatingByUserName(userName); }
	 */
}
