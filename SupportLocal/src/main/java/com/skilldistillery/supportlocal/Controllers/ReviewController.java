package com.skilldistillery.supportlocal.Controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.supportlocal.entities.Review;
import com.skilldistillery.supportlocal.services.ReviewService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class ReviewController {

	@Autowired
	private ReviewService reviewSvc;

	@GetMapping("users/{id}/reviews")
	public List<Review> userReviews(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response,
			Principal principal) {

		
		List<Review> reviews = reviewSvc.findUserReviews(principal.getName(), id);
		System.out.println("from controller" + reviews);
		if(reviews != null && reviews.size() == 0) {
			response.setStatus(204);
		}
		if(reviews == null) {
			response.setStatus(404);
		}
		return reviews;
	}
	
	
	@GetMapping("business/{businessId}/reviews")
	public List<Review> businessReviews(@PathVariable Integer businessId, HttpServletRequest request, HttpServletResponse response,
			Principal principal) {

		
		List<Review> reviews = reviewSvc.findBusinessReviews(businessId);
		System.out.println("from controller" + reviews);
		if(reviews != null && reviews.size() ==0) {
			response.setStatus(204);
		}
		if(reviews == null) {
			response.setStatus(404);
		}
		return reviews;
	}
	
	
	
	@PostMapping("users/{uid}/business/{bid}/reviews")
	public Review createReview(@RequestBody Review review, @PathVariable Integer uid,
			@PathVariable Integer bid,
			 HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			reviewSvc.createReview(principal.getName(), review, uid, bid);
		
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			res.addHeader("Location", url.toString());
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return review;
	}
	
	@PutMapping("users/{uid}/business/{bid}/reviews")
	public Review updateReview(@RequestBody Review review, @PathVariable int uid,
			@PathVariable int bid, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		try {
			review = reviewSvc.updateReview(principal.getName(), review, uid, bid);
			if (review == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			review = null;
		}
		return review;
	}
	
	@DeleteMapping("reviews/{uid}")
	public boolean deleteReview(@PathVariable int uid,
			 HttpServletRequest req, HttpServletResponse res,
			Principal principal) {	
		return reviewSvc.deleteReview(principal.getName(), uid);
		
	}

	
	
	

}
