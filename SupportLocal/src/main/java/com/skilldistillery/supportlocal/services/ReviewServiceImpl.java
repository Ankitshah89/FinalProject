package com.skilldistillery.supportlocal.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.supportlocal.entities.Review;
import com.skilldistillery.supportlocal.entities.Role;
import com.skilldistillery.supportlocal.entities.User;
import com.skilldistillery.supportlocal.repositories.ReviewRepository;
import com.skilldistillery.supportlocal.repositories.UserRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Review> findAll(String email, int id) {

		User user = userRepo.findUserByEmail(email);
		List<Review> results = new ArrayList<>();
		List<Review> reviews = new ArrayList<>();

		if (user != null) {

			for (Review review : reviews) {
				if (review.getId() == id) {
					results.add(review);
				}
			}
			return results;
		}

		return null;
	}

	@Override
	public Review createReview(String email, Review review, int id) {
		User user = userRepo.findUserByEmail(email);
		if (user.getId() == id || user.getRole().equals(Role.Admin)) {
			review.setDescription(email);
			;
			reviewRepo.saveAndFlush(review);
		}
		return review;
	}

	@Override
	public Review updateReview(String email, Review review, int id) {

		return null;
	}
}
