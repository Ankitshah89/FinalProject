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

	@GetMapping("users/reviews")
	public List<Review> userReviews(HttpServletRequest request, HttpServletResponse response, Principal principal) {

		List<Review> reviews = reviewSvc.findUserReviews(principal.getName());
		System.out.println("from controller" + reviews);
		if (reviews != null && reviews.size() == 0) {
			response.setStatus(204);
		}
		if (reviews == null) {
			response.setStatus(404);
		}
		return reviews;
	}
	@GetMapping("users/{uid}/reviews")
	public List<Review> userReviewsById(HttpServletRequest request, HttpServletResponse response,@PathVariable int uid) {
		
		List<Review> reviews = reviewSvc.findUserReviewsById(uid);
		System.out.println("from controller" + reviews);
		if (reviews != null && reviews.size() == 0) {
			response.setStatus(204);
		}
		if (reviews == null) {
			response.setStatus(404);
		}
		return reviews;
	}

	@GetMapping("business/{businessId}/reviews")
	public List<Review> businessReviews(@PathVariable Integer businessId, HttpServletRequest request,
			HttpServletResponse response, Principal principal) {

		List<Review> reviews = reviewSvc.findBusinessReviews(businessId);
		System.out.println("from controller" + reviews);
		if (reviews != null && reviews.size() == 0) {
			response.setStatus(204);
		}
		if (reviews == null) {
			response.setStatus(404);
		}
		return reviews;
	}

	@PostMapping("businesses/{bid}/reviews")
	public Review createReview(@RequestBody Review review, @PathVariable Integer bid, HttpServletRequest req,
			HttpServletResponse res, Principal principal) {
		try {
			reviewSvc.createReview(principal.getName(), review, bid);

			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			res.addHeader("Location", url.toString());
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
		}
		return review;
	}

	@PutMapping("businesses/{bid}/reviews/{rid}")
	public Review updateReview(@RequestBody Review review, @PathVariable int bid, @PathVariable int rid,
			HttpServletRequest req, HttpServletResponse res, Principal principal) {
		try {
			review = reviewSvc.updateReview(principal.getName(), review, bid, rid);
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
	public boolean deleteReview(@PathVariable int uid, HttpServletRequest req, HttpServletResponse res,
			Principal principal) {
		return reviewSvc.deleteReview(principal.getName(), uid);

	}

}
