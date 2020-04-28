package com.skilldistillery.supportlocal.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReviewCommentTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private ReviewComment reviewComment;

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
		reviewComment = em.find(ReviewComment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		reviewComment = null;
	}

	@Test
	void test() {
		assertNotNull(reviewComment);
//		assertEquals("More content", reviewComment.getContent());
	}


}
