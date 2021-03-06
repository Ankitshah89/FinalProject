
package com.skilldistillery.supportlocal.Controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.skilldistillery.supportlocal.entities.Article;
import com.skilldistillery.supportlocal.services.ArticleService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
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
				resp.setStatus(200);
			}
		}
		return articles;

	}
	
	@GetMapping("articles/business/{bid}")
	public List<Article> findAllByBusiness(HttpServletRequest req, HttpServletResponse resp, Principal principal, @PathVariable Integer bid) {
		List<Article> articles = articleSvc.indexBusiness(bid);
		
		if (articles == null) {
			resp.setStatus(404);
		}
		if (articles != null) {
			if (articles.size() == 0) {
				resp.setStatus(200);
			}
		}
		return articles;
		
	}
	
	@GetMapping("articles/user")
	public List<Article> findAllByUser(HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		List<Article> articles = articleSvc.indexUser(principal.getName());
		
		if (articles == null) {
			resp.setStatus(404);
		}
		if (articles != null) {
			if (articles.size() == 0) {
				resp.setStatus(200);
			}
		}
		return articles;
		
	}
	@GetMapping("articles/user/{uid}")
	public List<Article> findAllByUserId(HttpServletRequest req, HttpServletResponse resp,@PathVariable Integer uid) {
		List<Article> articles = articleSvc.findByUserId(uid);
		
		if (articles == null) {
			resp.setStatus(404);
		}
		if (articles != null) {
			if (articles.size() == 0) {
				resp.setStatus(200);
			}
		}
		return articles;
		
	}

	@GetMapping("articles/{aid}")
	public Article show(HttpServletRequest req, HttpServletResponse resp, Principal principal, @PathVariable int aid) {
		Article article = articleSvc.show(aid);
		if (article == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
			return article;
		}
		return null;
	}
	
	
	@PostMapping("articles")
	public Article createSingleArticle(HttpServletRequest req, HttpServletResponse res, @RequestBody Article article,
			Principal principal) {
		try {
			Article newArticle = articleSvc.createSingleArticle(principal.getName(), article);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(article.getId());
			String location = url.toString();
			res.addHeader("Location", location);
			return newArticle;

		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}


	@PostMapping("articles/business/{bid}")
	public Article create(HttpServletRequest req, HttpServletResponse res, @RequestBody Article article,
			Principal principal, @PathVariable int bid) {
		try {
			Article newArticle = articleSvc.create(principal.getName(), article, bid);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(article.getId());
			String location = url.toString();
			res.addHeader("Location", location);
			return newArticle;

		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}

	@PutMapping("articles/{aid}")
	public Article update(HttpServletRequest req, HttpServletResponse res, @PathVariable int aid,
			@RequestBody Article article, Principal principal) {
		Article updatedArticle = articleSvc.update(principal.getName(), aid, article);
		if (updatedArticle == null) {
			res.setStatus(400);
		} else {
			res.setStatus(200);
		}

		return updatedArticle;
	}

	@DeleteMapping("articles/{aid}")
	public boolean destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable("aid") Integer aid,
			Principal principal) {
		boolean isDeleted = articleSvc.destroy(principal.getName(), aid);
		if (isDeleted) {
			res.setStatus(200);
		} else {
			res.setStatus(404);
		}
		return isDeleted;
	}

}
