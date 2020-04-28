package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.Review;

public interface ReviewService {
	List<Review> findAll(String email, int id);

	Review createReview(String email, Review review, int id);

	Review updateReview(String email, Review review, int id);

}
