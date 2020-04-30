package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.ReviewComment;

public interface ReviewCommentService {
	
	List<ReviewComment> findAllComments();
	
	ReviewComment findCommentById(Integer id);
	
	ReviewComment createComment (ReviewComment comment, Integer userId, Integer reviewId);
	

	ReviewComment updateComment(Integer id, ReviewComment comment);

	boolean deleteComment(Integer id);
	

}
