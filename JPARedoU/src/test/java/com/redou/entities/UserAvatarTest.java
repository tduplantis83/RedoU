package com.redou.entities;

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

class UserAvatarTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private UserAvatar ua;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("RedoUPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		ua = em.find(UserAvatar.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		ua = null;
	}

	@Test
	@DisplayName("Get User Avatar Details Test")
	void test1() {
		assertEquals("Average", ua.getAvatar().getBodyType());
		
	}

	@Test
	@DisplayName("Get User Avatar to User Test")
	void test2() {
		assertEquals("travisd", ua.getUser().getUsername());
		
	}
}
