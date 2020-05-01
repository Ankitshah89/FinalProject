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
	public List<Article> indexBusiness(Integer bid) {
		return articleRepo.findByBusinessId(bid);
	}

	@Override
	public List<Article> indexUser(Integer uid) {
		return articleRepo.findByUserId(uid);
	}

	@Override
	public Article show(int aid) {
		Optional<Article> optArticle = articleRepo.findById(aid);
		System.out.println("optArticle: " + optArticle);
		if (optArticle.isPresent()) {
			Article article = optArticle.get();
			System.out.println("Article: " + article);
			if (article != null) {
				return article;
			}
		}
		System.out.println("am i returning null from impl?");
		return null;
	}

	@Override
	public Article create(String email, Article article, int bid) {
		User user = userRepo.findUserByEmail(email);
		if (user != null) {

			Optional<Business> optBusiness = busRepo.findById(bid);
			if (optBusiness != null || user != null) {
				Business business = optBusiness.get();

				article.setUser(user);
				article.setBusiness(business);
				articleRepo.saveAndFlush(article);
				return article;
			}
		}
		return null;

	}
	
	
	
	@Override
	public Article createSingleArticle(String email, Article article) {
		User user = userRepo.findUserByEmail(email);
		if (user != null) {
				article.setUser(user);
				articleRepo.saveAndFlush(article);
				return article;
			
		}
		return null;

	}

	@Override
	public Article update(String email, int aid, Article article) {
		User user = userRepo.findUserByEmail(email);
		if (user != null) {

			Optional<Article> optArticle = articleRepo.findById(aid);
			if (optArticle != null) {
				Article managed = optArticle.get();
				if (managed.getUser().getId() == user.getId()
						|| managed.getBusiness().getManager().getId() == user.getId()
						|| user.getRole().equals(Role.Admin)) {

					managed.setTitle(article.getTitle());
					managed.setContent(article.getContent());
					managed.setImageUrl(article.getImageUrl());
					articleRepo.saveAndFlush(managed);
					return managed;
				}
			}
		}
		return null;
	}

	@Override
	public boolean destroy(String email, int aid) {
		User user = userRepo.findUserByEmail(email);
		if (user != null) {

			Optional<Article> optArticle = articleRepo.findById(aid);
			if (optArticle != null) {
				Article managed = optArticle.get();
				if(managed.getUser().getId() == user.getId() ||
						user.getRole().equals(Role.Admin) ||
						managed.getBusiness().getManager().getId() == user.getId()) {
					
					
				articleRepo.deleteById(aid);
				return true;
				}
			}

		}
		return false;
	}

}

// find by business, user
// find all by business, title, content, user