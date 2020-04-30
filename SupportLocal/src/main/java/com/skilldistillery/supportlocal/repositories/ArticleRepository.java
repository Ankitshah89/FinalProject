package com.skilldistillery.supportlocal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.supportlocal.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	

}
