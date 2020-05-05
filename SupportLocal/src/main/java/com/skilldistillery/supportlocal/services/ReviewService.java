package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.Review;

public interface ReviewService {
	List<Review> findUserReviews(String email);
	
	List<Review> findBusinessReviews ( Integer id);

	Review createReview(String email, Review review, Integer bid);

	Review updateReview(String email, Review review, Integer bid, Integer rid);
	
	
	Boolean deleteReview (String email, Integer id);

	List<Review> findUserReviewsById(int uid);

}
