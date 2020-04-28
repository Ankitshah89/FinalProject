package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.Review;

public interface ReviewService {
	List<Review> findAll (String username, int id);
	
	Review createReview(String username, Review review, int id);
	
	Review updateReview(String username, Review review, int id);
	
	

}
