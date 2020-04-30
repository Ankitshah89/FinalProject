package com.skilldistillery.supportlocal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.supportlocal.entities.Article;
import com.skilldistillery.supportlocal.entities.Business;
import com.skilldistillery.supportlocal.entities.Role;
import com.skilldistillery.supportlocal.entities.User;
import com.skilldistillery.supportlocal.repositories.ArticleRepository;
import com.skilldistillery.supportlocal.repositories.BusinessRepository;
import com.skilldistillery.supportlocal.repositories.UserRepository;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private BusinessRepository busRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ArticleRepository articleRepo;

	@Override
	public List<Article> index() {
		return articleRepo.findAll();
	}

	@Override
	public Article show(String email, int aid) {
		System.out.println("AID: " + aid);
		System.out.println("Email: " + email);
		User user = userRepo.findUserByEmail(email);
		System.out.println("User: " + user);
		Optional<Article> optArticle = articleRepo.findById(aid);
		System.out.println("optArticle: " + optArticle);
		if (optArticle.isPresent()) {
			Article article = optArticle.get();
			System.out.println("Article: " + article);
			if (article != null) {
				if (user.getId() == article.getUser().getId() || user.getRole().equals(Role.Admin)) {
					System.out.println("In return article");
				return article;
				}
			}
		}
		System.out.println("am i returning null from impl?");
		return null;
	}

	@Override
	public Article create(String email, Article article, int uid, int bid) {
		User user = userRepo.findUserByEmail(email);
		Optional<Business> optBusiness = busRepo.findById(bid);
		if (optBusiness != null || user != null) {
			Business business = optBusiness.get();
			if (user.getId() == uid || user.getRole().equals(Role.Admin)) {
				article.setUser(user);
				article.setBusiness(business);
				articleRepo.saveAndFlush(article);
				return article;
			}
		}
		return null;

	}

	@Override
	public Article update(String email, int aid, Article article) {
		User user = userRepo.findUserByEmail(email);
		Optional<Article> optArticle = articleRepo.findById(aid);
		if (optArticle != null) {
			Article managed = optArticle.get();
			if (user.getId() == managed.getUser().getId() || user.getRole().equals(Role.Admin)) {
				managed.setTitle(article.getTitle());
				managed.setContent(article.getContent());
				managed.setImageUrl(article.getImageUrl());
				articleRepo.saveAndFlush(managed);
				return managed;
			}
		}
		return null;
	}

	@Override
	public boolean destroy(String email, int aid) {
		User user = userRepo.findUserByEmail(email);
		Optional<Article> optArticle = articleRepo.findById(aid);
		if (optArticle != null) {
			Article managed = optArticle.get();
			if (user.getId() == managed.getUser().getId() || user.getRole().equals(Role.Admin)) {
				articleRepo.deleteById(aid);
				return true;
			}

		}
		return false;
	}
}

// find by business, user
// find all by business, title, content, user