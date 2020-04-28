package com.skilldistillery.supportlocal.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArticleTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Article article;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("SupportLocalPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
		
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		article = em.find(Article.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		article = null;
	}

	@Test
	@DisplayName("Article Entity Mapping")
	void test() {
		assertNotNull(article);
		
	}
	@Test
	@DisplayName("Article to User mapping")
	void test1() {
		assertEquals("Jason", article.getUser().getFirstName());
	}
	@Test
	@DisplayName("Article to Business Mapping")
	void test2() {
		assertEquals("Mount Rushmore", article.getBusiness().getName());
	}

}
