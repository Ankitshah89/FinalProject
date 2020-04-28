package com.skilldistillery.supportlocal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.supportlocal.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	


}
