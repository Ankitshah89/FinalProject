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

class PreferenceTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Preference pref;

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
		pref = em.find(Preference.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		pref = null;
	}

	@Test
	@DisplayName("Testing Preference mapping without relationships")
	void test() {
		assertNotNull(pref);
		assertEquals("climbing", pref.getPreferenceType());
		assertEquals("Sports", pref.getPreferenceCategory().toString());
		
	}
	
	@Test
	@DisplayName("Relationship Mapping between Preference/Business")
	void test2() {
		assertEquals("Mount Rushmore", pref.getBuinesses().get(0).getName());
	}
	
	@Test
	@DisplayName("Relationship Mapping between Preference/User")
	void test3() {
		assertEquals("Jason", pref.getUsers().get(0).getFirstName());
	}

}
