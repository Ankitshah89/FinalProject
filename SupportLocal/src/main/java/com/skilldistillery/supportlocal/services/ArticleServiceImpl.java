package com.skilldistillery.supportlocal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.supportlocal.entities.Article;
import com.skilldistillery.supportlocal.entities.Business;
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
		Optional<Article> optArticle = articleRepo.findById(aid);
		if (optArticle.isPresent()) {
			Article article = optArticle.get();
			if (article != null) {
				if (article.getUser().getEmail().equals(email))
					;

				return article;
			}
		}
		return null;
	}

	// HAVE NOT FINISHED YET

	@Override
	public Article create(String email, Article article) {
		User user = userRepo.findUserByEmail(email);
		Business business = busRepo.findByManager(user);
		if (user != null) {
			article.setUser(user);
			article.setBusiness(business);
			articleRepo.saveAndFlush(article);
		} else {
			article = null;
		}
		return article;
	}

	@Override
	public Article update(String email, int aid, Article article) {
		Optional<Article> managed = articleRepo.findById(aid);
		if (managed.isPresent() && article.getUser().getEmail().equals(email)) {
			Article managedArticle = managed.get();
			article.setId(managedArticle.getId());
			articleRepo.saveAndFlush(article);
		} else {
			return null;

		}
		return article;
	}

	@Override
	public boolean destroy(String email, int aid) {
		System.out.println("Here is article id " + aid);
		System.out.println("Here is email " + email);
		Optional<Article> managed = articleRepo.findById(aid);
		System.out.println("Here is managed " + managed);
		if (managed.isPresent()) {
			Article managedDelete = managed.get();
			System.out.println("Here is managedDelete " + managedDelete);
			System.out.println("Here is managedDelete email " + managedDelete.getUser().getEmail());
			System.out.println(managedDelete.getUser().getEmail() == email);
			if (managedDelete != null && managedDelete.getUser().getEmail().equals(email)) {
				articleRepo.deleteById(aid);
				return true;
			}
		}
		return false;

	}

	// find by business, user
	// find all by business, title, content, user
}
