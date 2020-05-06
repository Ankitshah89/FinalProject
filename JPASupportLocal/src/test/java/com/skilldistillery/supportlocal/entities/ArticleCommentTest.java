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

class ArticleCommentTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private ArticleComment articleComment;

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
		articleComment = em.find(ArticleComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		articleComment = null;
	}

//	@Test
//	@DisplayName("ArticleComment Entity Mapping")
//	void test() {
//		assertNotNull(articleComment);
//	}
//	@Test
//	@DisplayName("ArticleComment to  article Entity Mapping")
//	void test1() {
//		assertEquals("Trial and Error",articleComment.getArticle().getContent());
//	}
//	@Test
//	@DisplayName("ArticleComment to parentComment Entity Mapping")
//	void test2() {
//		assertEquals("Check Check",articleComment.getParentComment().getContent());
//	}
//	@Test
//	@DisplayName("ArticleComment to User Entity Mapping")
//	void test3() {
//		assertEquals("Jason",articleComment.getUser().getFirstName());
//	}


}