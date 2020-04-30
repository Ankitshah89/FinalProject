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

	@Autowired
	private BusinessRepository businessRepo;

	@Override
	public List<Review> findUserReviews(String email) {

		User user = userRepo.findUserByEmail(email);
		List<Review> results = new ArrayList<>();
		List<Review> reviews = new ArrayList<>();

		if (user != null) {
			reviews = reviewRepo.findByActiveTrueAndUser_Id(user.getId());
			return reviews;
		}

		return null;
	}

	@Override
	public Review createReview(String email, Review review, Integer bid) {
		User user = userRepo.findUserByEmail(email);
		if (user != null || user.getRole().equals(Role.Admin)) {

			Business business;
			Optional<Business> opt = businessRepo.findById(bid);

			if (opt.isPresent()) {
				business = opt.get();
				System.out.println(business);

				review.setUser(user);
				review.setBusiness(business);

				reviewRepo.saveAndFlush(review);

				System.out.println(review);
			}
		}
		return review;
	}

	@Override
	public Review updateReview(String email, Review review, Integer bid, Integer rid) {

		User user = userRepo.findUserByEmail(email);
		Business business;
		Review existing = null;

		if (user != null || user.getRole().equals(Role.Admin)) {

			Optional<Business> opt = businessRepo.findById(bid);

	
			Optional<Review> revOpt = reviewRepo.findById(rid);
			if (opt.isPresent()) {
				business = opt.get();
				
				if(revOpt.isPresent()) {
				review = revOpt.get();
				
				review.setBusiness(business);
				existing.setRating(review.getRating());
				existing.setDescription(review.getDescription());
				reviewRepo.saveAndFlush(existing);
				}
			}
			return existing;
			
			}
				
			else {
				return null;
			}
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
