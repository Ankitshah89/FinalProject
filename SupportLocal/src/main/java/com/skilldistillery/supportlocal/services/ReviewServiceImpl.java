package com.skilldistillery.supportlocal.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.supportlocal.entities.Business;
import com.skilldistillery.supportlocal.entities.Review;
import com.skilldistillery.supportlocal.entities.Role;
import com.skilldistillery.supportlocal.entities.User;
import com.skilldistillery.supportlocal.repositories.BusinessRepository;
import com.skilldistillery.supportlocal.repositories.ReviewRepository;
import com.skilldistillery.supportlocal.repositories.UserRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepo;

	@Autowired
	private UserRepository userRepo;
////	
//	@Autowired
//	private BusinessService businessSvc;
	
	@Autowired
	private BusinessRepository businessRepo;

	@Override
	public List<Review> findUserReviews(String email, Integer id) {

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
	public Review createReview(String email, Review review, Integer id, Integer bid) {
		User user = userRepo.findUserByEmail(email);
		
		Business business;
		Optional<Business> opt = businessRepo.findById(bid);
		
		if(opt.isPresent()) {
			business = opt.get();
			System.out.println(business);
		
		if (user.getId() == id || user.getRole().equals(Role.Admin)) {
		System.out.println("REvieeww" +review);
		
		review.setUser(user);
		review.setBusiness(business);
		}
		
		reviewRepo.saveAndFlush(review);
		
		System.out.println(review);
		}
		return review;
	}

	@Override
	public Review updateReview(String email, Review review, Integer id, Integer bid) {
	
		User user = userRepo.findUserByEmail(email);
		Optional<Business> opt = businessRepo.findById(bid);
		
		Business business;
		Review existing = null;

		if (opt.isPresent()) {
			business = opt.get();
			if (user.getId() == id || user.getRole().equals(Role.Admin)) {
				Optional<Review> optRev = reviewRepo.findById(id);
				review.setBusiness(business);

				if (optRev.isPresent()) {
					existing = optRev.get();
					existing.setRating(review.getRating());
					existing.setDescription(review.getDescription());	
					reviewRepo.saveAndFlush(existing);
				} else {
					return null;
				}
			}
		}
		return existing;
	}
	
	

	@Override
	public List<Review> findBusinessReviews(Integer id) {
		return reviewRepo.findByBusinessId(id);
	}

	

	@Override
	public Boolean deleteReview(String email, Integer id) {
		User user = userRepo.findUserByEmail(email);
		if (user.getId() == id || user.getRole().equals(Role.Admin)) {
		reviewRepo.deleteById(id);
		return true;
		}
		return false;
	}

}
