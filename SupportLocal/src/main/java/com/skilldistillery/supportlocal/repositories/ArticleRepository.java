package com.skilldistillery.supportlocal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.supportlocal.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
	List<Article> findByBusinessId(int id);
	
	List<Article> findByUserId(int id);
	
	List<Article> findByUser_Email(String email);
	
}
