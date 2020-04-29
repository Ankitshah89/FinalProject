package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.Review;

public interface ReviewService {
	List<Review> findUserReviews(String email, int id);
	
	List<Review> findBusinessReviews ( int id);

	Review createReview(String email, Review review, int uid , int bid);

	Review updateReview(String email, Review review, int id);

}
