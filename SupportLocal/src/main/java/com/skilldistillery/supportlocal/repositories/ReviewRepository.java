package com.skilldistillery.supportlocal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.supportlocal.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	List<Review> findByActiveTrue();
	
	List<Review> findByBusinessId(int id);
	
	List<Review> findByActiveTrueAndUser_Id(int id);

}
