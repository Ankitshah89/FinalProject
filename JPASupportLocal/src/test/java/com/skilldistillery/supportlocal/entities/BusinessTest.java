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

class BusinessTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Business business;

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
		business = em.find(Business.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		business = null;
	}

	@Test
	@DisplayName("Testing Business without relationships")
	void test() {
		
		assertNotNull(business);
		assertEquals(1, business.getId());
		assertEquals("Mount Rushmore", business.getName());
//		assertEquals("Great Rock Climbing place", review.getDescription());
	}
	
	@Test
	@DisplayName("Testing Business Relationship between Business/User")
	void test2() {
		assertNotNull(business.getManager());
		assertEquals("Jason", business.getManager().getFirstName());
	}

}
