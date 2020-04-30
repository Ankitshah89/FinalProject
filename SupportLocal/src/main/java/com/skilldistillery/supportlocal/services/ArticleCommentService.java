
package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.ArticleComment;

public interface ArticleCommentService {

	 public List<ArticleComment> index();

			ArticleComment show(int cid);

		    ArticleComment create(String email, ArticleComment comment, Integer aid);
		    
		    ArticleComment createReplyTo(String email, ArticleComment reply, Integer cid);
//
			ArticleComment update(String email, int aid, ArticleComment comment);
//
			boolean destroy(String email, int cid);


}

