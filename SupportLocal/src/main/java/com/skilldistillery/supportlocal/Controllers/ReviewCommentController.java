package com.skilldistillery.supportlocal.Controllers;

import java.util.List;

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

import com.skilldistillery.supportlocal.entities.ReviewComment;
import com.skilldistillery.supportlocal.services.ReviewCommentService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class ReviewCommentController {
	
	
	@Autowired
	ReviewCommentService svc;
	
	
	
	@GetMapping("comments")
	public List<ReviewComment> showAllComments(HttpServletResponse resp){
		List<ReviewComment> comments = svc.findAllComments();
		if (comments.size() > 0) {
			return comments;
		}
		else {
			resp.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("comments/{id}")
	public ReviewComment findCommentById(@PathVariable Integer id, HttpServletResponse resp) {
		ReviewComment comment = svc.findCommentById(id);
		if(comment != null) {
			return comment;
		}else {
			resp.setStatus(404);
			return null;
		}
	}
	

	@PostMapping("comments/{userid}/{reviewid}")
	public ReviewComment createNewComment(
			@RequestBody ReviewComment comment,
			@PathVariable Integer userid,
			@PathVariable Integer reviewid,
			HttpServletResponse resp
		){
		System.out.println(comment.getReview());
		ReviewComment newComment = svc.createComment(comment, userid, reviewid);
		if (newComment != null) {
			return newComment;
		}
		else {
			resp.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("comments/{id}")
	public ReviewComment updateComment(@RequestBody ReviewComment comment, @PathVariable int id, HttpServletResponse response){
		ReviewComment editComment = svc.updateComment(id, comment);
		if (editComment != null) {
			return editComment;
		}
		else {
			response.setStatus(404);
			return null;
		}
	}
	
	@DeleteMapping("comments/{id}")
	public void deleteComment(@PathVariable int id, HttpServletResponse resp){
		boolean result = false;
		try {
			result = svc.deleteComment(id);
			if (result == true) {
				resp.setStatus(204);
			}
		} catch (Exception e) {			
			resp.setStatus(404);
		}
	}
	
}