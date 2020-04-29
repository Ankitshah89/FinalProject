package com.skilldistillery.supportlocal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.supportlocal.entities.Article;
import com.skilldistillery.supportlocal.repositories.ArticleRepository;
import com.skilldistillery.supportlocal.repositories.UserRepository;


@Service
public class ArticleServiceImpl implements ArticleService {
	
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

	@Override
	public Article create(String email, Article article) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article update(String email, int aid, Article article) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean destroy(String username, int aid) {
		// TODO Auto-generated method stub
		return false;
	}

	// find by business, user
	// find all by business, title, content, user
}
