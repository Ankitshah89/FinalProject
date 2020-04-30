package com.skilldistillery.supportlocal.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.supportlocal.entities.Review;
import com.skilldistillery.supportlocal.entities.ReviewComment;
import com.skilldistillery.supportlocal.entities.User;
import com.skilldistillery.supportlocal.repositories.ReviewCommentRepository;
import com.skilldistillery.supportlocal.repositories.ReviewRepository;
import com.skilldistillery.supportlocal.repositories.UserRepository;

@Service
public class ReviewCommentServiceImpl implements ReviewCommentService {
	
	@Autowired 
	 private ReviewCommentRepository reviewCommentrepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ReviewRepository reviewRepo;

	

	@Override
	public List<ReviewComment> findAllComments() {
		return reviewCommentrepo.findAll();
	}

	@Override
	public ReviewComment findCommentById(Integer id) {
		Optional<ReviewComment> comment = reviewCommentrepo.findById(id);
		if(comment.isPresent()) {
			return comment.get();
		}
		else {
			return null;
		}
	}
	@Override
	public ReviewComment createComment(ReviewComment comment, Integer userId, Integer reviewId) {
		Optional<Review> rOpt = reviewRepo.findById(reviewId);
		if (rOpt.isPresent()) {
			comment.setReview(rOpt.get());			
		}else {
			return null;
		}
		Optional<User> uOpt = userRepo.findById(userId);
		if (uOpt.isPresent()) {
			comment.setUser(uOpt.get());	
		}else {
			return null;
		}
		comment.setCreatedAt(LocalDateTime.now());
		ReviewComment newComment = reviewCommentrepo.saveAndFlush(comment);
		if (newComment != null) {
			return newComment;
		}
		return null;
	}

	@Override
	public ReviewComment updateComment(Integer id, ReviewComment comment) {
		Optional<ReviewComment> opt = reviewCommentrepo.findById(id);
		if (opt.isPresent()) {
			ReviewComment managed = opt.get();
			managed.setId(id);
			managed.setContent(comment.getContent());
			managed.setActive(comment.isActive());
			managed.setUpdatedAt(LocalDateTime.now());
			return reviewCommentrepo.saveAndFlush(managed);
		}
		return null;
	}

	@Override
	public boolean deleteComment(Integer id) {
		boolean result = false;
		Optional<ReviewComment> comment = reviewCommentrepo.findById(id);
		if (comment.isPresent()) {
			reviewCommentrepo.deleteById(id);
			result = true;
		}
		
		return result;
	}

}
