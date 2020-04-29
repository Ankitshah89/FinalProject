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
//	
//	@Autowired
//	private Busines

	@Override
	public List<Review> findUserReviews(String email, int id) {

		User user = userRepo.findUserByEmail(email);
		List<Review> results = new ArrayList<>();
		List<Review> reviews = new ArrayList<>();

		if (user != null) {
			
			reviews = reviewRepo.findByActiveTrue();
			System.out.println(reviews);
			
			for (Review review : reviews) {
				if (review.getUser().getId() == id) {
					results.add(review);
				}
			}
			return results;
		}
		return null;
	}

	@Override
	public Review createReview(String email, Review review, int id, int bid) {
		User user = userRepo.findUserByEmail(email);
		
//		Business
		
		if (user.getId() == id || user.getRole().equals(Role.Admin)) {
		System.out.println("REvieeww" +review);
			if(review.getBusiness().getId() == bid) {
				
//				review.setDescription();
				 return reviewRepo.saveAndFlush(review);
			}
			
				
		}
		return null;
	}

	@Override
	public Review updateReview(String email, Review review, int id) {

		return null;
	}

	@Override
	public List<Review> findBusinessReviews(int id) {
		return reviewRepo.findByBusinessId(id);
	}
}
