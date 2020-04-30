package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.Article;

public interface ArticleService {
	
	 public List<Article> index();

//	Article show(int aid);

	    Article create(String email, Article article, int uid, int bid);

		Article update(String email, int aid, Article article);

		boolean destroy(String email, int aid);

		Article show(String email, int aid);



}
