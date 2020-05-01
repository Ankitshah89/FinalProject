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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.supportlocal.entities.ArticleComment;
import com.skilldistillery.supportlocal.services.ArticleCommentService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class ArticleCommentController {
	
	@Autowired
	private ArticleCommentService commentSvc;

	@GetMapping("articles/comments")
	public List<ArticleComment> findAll(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		List<ArticleComment> comments = commentSvc.index();

		if (comments == null) {
			resp.setStatus(404);
		}
		if (comments != null) {
			if (comments.size() == 0) {
				resp.setStatus(200);
			}
		}
		return comments;

	}

	@GetMapping("articles/comments/{cid}")
	public ArticleComment show(HttpServletRequest req, HttpServletResponse resp, Principal principal, @PathVariable int cid) {
		ArticleComment comments = commentSvc.show(cid);
		if (comments == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
			return comments;
		}
		return null;
	}

	@PostMapping("articles/{aid}/comments")
	public ArticleComment create(HttpServletRequest req, HttpServletResponse res, @RequestBody ArticleComment comments,
			Principal principal, @PathVariable Integer aid) {
		try {
			ArticleComment newComments = commentSvc.create(principal.getName(), comments, aid);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(comments.getId());
			String location = url.toString();
			res.addHeader("Location", location);
			return newComments;

		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	@PostMapping("articles/{aid}/comments/{cid}")
	public ArticleComment createReplyTo(HttpServletRequest req, HttpServletResponse res, @RequestBody ArticleComment reply,
			Principal principal, @PathVariable Integer cid) {
		try {
			ArticleComment newComments = commentSvc.createReplyTo(principal.getName(), reply, cid);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(reply.getId());
			String location = url.toString();
			res.addHeader("Location", location);
			return newComments;
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	@PutMapping("articles/comments/{cid}")
	@ResponseBody
	public ArticleComment update(HttpServletRequest req, HttpServletResponse res, @PathVariable int cid,
			@RequestBody ArticleComment comments, Principal principal) {
		ArticleComment updatedComments = commentSvc.update(principal.getName(), cid, comments);
		if (updatedComments == null) {
			res.setStatus(400);
		} else {
			res.setStatus(200);
		}

		return updatedComments;
	}

	@DeleteMapping("articles/comments/{cid}")
	public boolean destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable("cid") Integer cid,
			Principal principal) {
		boolean isDeleted = commentSvc.destroy(principal.getName(), cid);
		if (isDeleted) {
			res.setStatus(200);
		} else {
			res.setStatus(404);
		}
		return isDeleted;
	}

}