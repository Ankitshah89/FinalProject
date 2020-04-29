package com.skilldistillery.supportlocal.Controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.supportlocal.entities.Article;
import com.skilldistillery.supportlocal.services.ArticleService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4220"})
public class ArticleController {

	@Autowired
	private ArticleService articleSvc;
	
	@GetMapping("articles")
	public List<Article> findAll(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		List<Article> articles = articleSvc.index();
		
		if (articles == null) {
			resp.setStatus(404);
		}
		if (articles != null) {
			if (articles.size() == 0) {
				resp.setStatus(204);
			}
		}
		return articles;
		
	}
}
