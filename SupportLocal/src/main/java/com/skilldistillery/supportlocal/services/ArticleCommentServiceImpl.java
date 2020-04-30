package com.skilldistillery.supportlocal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.supportlocal.entities.ArticleComment;
import com.skilldistillery.supportlocal.entities.Business;
import com.skilldistillery.supportlocal.entities.Role;
import com.skilldistillery.supportlocal.entities.User;
import com.skilldistillery.supportlocal.repositories.ArticleCommentRepository;
import com.skilldistillery.supportlocal.repositories.BusinessRepository;
import com.skilldistillery.supportlocal.repositories.UserRepository;

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

	@Autowired
	private BusinessRepository busRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ArticleCommentRepository commentRepo;

	@Override
	public List<ArticleComment> index() {
		return commentRepo.findAll();
	}

//	@Override
//	public ArticleComment show(int cid) {
//		Optional<ArticleComment> optArticle = articleRepo.findById(cid);
//		ArticleComment article = optArticle.get();
//		return article;
//	}
//
	@Override
	public ArticleComment create(String email, ArticleComment comment, int uid, int aid) {
		User user = userRepo.findUserByEmail(email);
		Optional<ArticleComment> optComment = commentRepo.findById(aid);
		if (optComment != null || user != null) {
			ArticleComment managed = optComment.get();
			if (user.getId() == uid || user.getRole().equals(Role.Admin)) {
				comment.setUser(user);
				comment.setA
				commentRepo.saveAndFlush(comment);
				return comment;
			}
		}
		return null;

	}
//
//	@Override
//	public ArticleComment update(String email, int aid, ArticleComment article) {
//		User user = userRepo.findUserByEmail(email);
//		Optional<ArticleComment> optArticle = articleRepo.findById(aid);
//		if (optArticle != null) {
//			ArticleComment managed = optArticle.get();
//			if (user.getId() == managed.getUser().getId() || user.getRole().equals(Role.Admin)) {
//				managed.setTitle(article.getTitle());
//				managed.setContent(article.getContent());
//				managed.setImageUrl(article.getImageUrl());
//				articleRepo.saveAndFlush(managed);
//				return managed;
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public boolean destroy(String email, int aid) {
//		User user = userRepo.findUserByEmail(email);
//		Optional<ArticleComment> optArticle = articleRepo.findById(aid);
//		System.out.println("here i am");
//		if (optArticle != null) {
//			System.out.println("in opt Null check");
//			ArticleComment managed = optArticle.get();
//			System.out.println("managed id " + managed.getUser().getId());
//			System.out.println("user id" + user.getId());
//			System.out.println("user role" + user.getRole());
//			if (user.getId() == managed.getUser().getId() || user.getRole().equals(Role.Admin)) {
//				System.out.println("in delete");
//				articleRepo.deleteById(aid);
//				return true;
//			}
//
//		}
//		return false;
//	}

}
