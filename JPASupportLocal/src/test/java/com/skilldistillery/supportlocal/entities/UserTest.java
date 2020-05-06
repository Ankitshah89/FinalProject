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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test() {
		assertNotNull(user);
		assertEquals("Jason", user.getFirstName());
	}
	
	@Test
	@DisplayName("Relationship Mapping ManyToMany bewteen User/Business favourties")
	void test2() {
		assertEquals("Monkey Business Burritos", user.getFavoriteBusinesses().get(0).getName());
	}
	
//	@Test
//	@DisplayName("Relationship Mapping ManyToMany between User/Preference")
//	void test3() {
//		assertEquals("climbing", user.getPreferences().get(0).getPreferenceType());
//	}
//	
//	@Test
//	@DisplayName("Relationship Mapping OneToMany bewteen User/ArticleComment")
//	void test4() {
//		assertEquals("Check Check", user.getArticleComments().get(0).getContent());
//	}


}
