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

class AvatarTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Avatar av;

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
		av = em.find(Avatar.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		av = null;
	}

	@Test
	@DisplayName("Get Avatar Details Test")
	void test1() {
		assertEquals("https://i.imgur.com/uJaRmvQ.jpg", av.getAvatarUrl());
	}

	@Test
	@DisplayName("Get Avatar to User Details Test")
	void test2() {
		assertEquals("travisd", av.getUser().getUsername());
	}

}
