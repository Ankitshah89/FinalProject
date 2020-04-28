package com.skilldistillery.supportlocal.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AddressTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Address address;

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
		address = em.find(Address.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		address = null;
	}

	@Test
	@DisplayName("Address testing without relationship")
	void test() {
		assertNotNull(address);
		assertEquals("123 Street", address.getStreet());
		assertNull(address.getStreet2());
		assertEquals("Centennail", address.getCity());
		assertEquals("CO", address.getState());
		assertEquals("80112", address.getPostalCode());
		assertEquals("USA", address.getCountry());
	}
	
	@Test
	@DisplayName("Address Test Relational Mapping between Address/Business")
	void test2() {
		assertEquals("Mount Rushmore", address.getBusiness().getName());
		assertEquals("Rock climbing", address.getBusiness().getDescription());
		assertEquals("123456789", address.getBusiness().getPhone());
		assertTrue(address.getBusiness().isActive());
		assertEquals(1,address.getBusiness().getManager().getId());
	}
	
}
