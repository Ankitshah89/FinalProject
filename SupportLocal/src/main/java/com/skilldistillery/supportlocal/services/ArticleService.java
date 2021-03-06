package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.Article;

public interface ArticleService {
	
		public List<Article> index();
		
		public List<Article> indexBusiness(Integer bid);
		
		public List<Article> indexUser(String email);

	    Article create(String email, Article article, int bid);
	    
	    Article createSingleArticle(String email, Article article);

		Article update(String email, int aid, Article article);

		boolean destroy(String email, int aid);

		Article show(int aid);

		List<Article> findByUserId(Integer uid);



}
