package com.skilldistillery.supportlocal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.supportlocal.entities.ArticleComment;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Integer> {

}
