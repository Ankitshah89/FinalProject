package com.skilldistillery.supportlocal.entities;

import java.util.List;

public class YelpRating {
	
	private String rating; 
	private List<YelpReview> reviews;
	
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public List<YelpReview> getReviews() {
		return reviews;
	}
	public void setReviews(List<YelpReview> reviews) {
		this.reviews = reviews;
	}  
	
	
	
	
	

}
