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

class PostTopicTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private PostTopic pt;

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
		pt = em.find(PostTopic.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		pt = null;
	}

	@Test
	@DisplayName("Get Post Topic Test")
	void test1() {
		assertEquals("Diet", pt.getTopicName());			
	}
	
	@Test
	@DisplayName("Get Post Topic to Post Test")
	void test2() {
		assertEquals("Intermittent Fasting", pt.getPosts().get(0).getTitle());			
	}

}
