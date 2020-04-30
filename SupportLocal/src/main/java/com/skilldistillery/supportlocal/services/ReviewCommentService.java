package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.ReviewComment;

public interface ReviewCommentService {
	
	List<ReviewComment> findAllComments();
	
	ReviewComment findCommentById(Integer id);
	
	ReviewComment createComment (String email,ReviewComment comment, Integer reviewId);

	ReviewComment updateComment(String email,Integer id, ReviewComment comment);

	boolean deleteComment(String email,Integer id);
	

}
