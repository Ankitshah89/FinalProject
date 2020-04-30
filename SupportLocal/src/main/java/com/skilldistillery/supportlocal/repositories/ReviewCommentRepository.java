package com.skilldistillery.supportlocal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.supportlocal.entities.ReviewComment;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Integer> {

}
