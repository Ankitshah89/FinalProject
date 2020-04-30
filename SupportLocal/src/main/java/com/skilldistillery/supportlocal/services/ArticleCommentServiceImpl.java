package com.skilldistillery.supportlocal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.supportlocal.entities.Article;
import com.skilldistillery.supportlocal.entities.ArticleComment;
import com.skilldistillery.supportlocal.entities.Role;
import com.skilldistillery.supportlocal.entities.User;
import com.skilldistillery.supportlocal.repositories.ArticleCommentRepository;
import com.skilldistillery.supportlocal.repositories.ArticleRepository;
import com.skilldistillery.supportlocal.repositories.UserRepository;

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

	@Autowired
	private ArticleRepository articleRepo;
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ArticleCommentRepository commentRepo;

	@Override
	public List<ArticleComment> index() {
		return commentRepo.findAll();
	}

	@Override
	public ArticleComment show(int cid) {
		Optional<ArticleComment> optArticle = commentRepo.findById(cid);
		ArticleComment article = optArticle.get();
		return article;
	}

	@Override
	public ArticleComment create(String email, ArticleComment comment, Integer aid) {
		User user = userRepo.findUserByEmail(email);
		Optional<Article> optArticle = articleRepo.findById(aid);
		if (optArticle != null && user != null) {
			Article managed = optArticle.get();
			comment.setUser(user);
			comment.setArticle(managed);
			commentRepo.saveAndFlush(comment);
			return comment;
		}

		return null;

	}

	@Override
	public ArticleComment createReplyTo(String email, ArticleComment reply, Integer cid) {
		User user = userRepo.findUserByEmail(email);
		Optional<ArticleComment> optOriginal = commentRepo.findById(cid);
		if (optOriginal != null && user != null) {
			ArticleComment original = optOriginal.get();
			Article article = original.getArticle();
			reply.setUser(user);
			reply.setArticle(article);
			reply.setInReplyTo(original);
			commentRepo.saveAndFlush(reply);
			return reply;
		}

		return null;

	}

	@Override
	public ArticleComment update(String email, int cid, ArticleComment comment) {
		User user = userRepo.findUserByEmail(email);
		Optional<ArticleComment> optArticle = commentRepo.findById(cid);
		if (optArticle != null && user != null) {
			ArticleComment managed = optArticle.get();
			managed.setContent(comment.getContent());
			commentRepo.saveAndFlush(managed);
			return managed;

		}
		return null;
	}

	@Override
	public boolean destroy(String email, int cid) {
		User user = userRepo.findUserByEmail(email);
		Optional<ArticleComment> optComment = commentRepo.findById(cid);
		System.out.println("here i am");
		if (optComment != null) {
			System.out.println("in opt Null check");
			ArticleComment managed = optComment.get();
			System.out.println("managed id " + managed.getUser().getId());
			System.out.println("user id" + user.getId());
			System.out.println("user role" + user.getRole());
			if (user.getId() == managed.getUser().getId() || user.getRole().equals(Role.Admin)) {
				System.out.println("in delete");
				commentRepo.deleteById(cid);
				return true;
			}

		}
		return false;
	}

}
