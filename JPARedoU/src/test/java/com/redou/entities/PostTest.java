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

class PostTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Post p;

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
		p = em.find(Post.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		p = null;
	}

	@Test
	@DisplayName("Get Post Test")
	void test1() {
		assertEquals(
				"I'm curious how intermittent fasting works. Can anyone give me some details about it and the different methods?",
				p.getContent());
	}

	@Test
	@DisplayName("Get Post to Posting User Test")
	void test2() {
		assertEquals("emilyd", p.getUser().getUsername());
	}

	@Test
	@DisplayName("Get Post to Post Reply Test")
	void test3() {
		assertEquals(
				"There are several different types of intermittent fasting including: 16 hours fasting with 8 hours eating, One Meal A Day (OMAD). The general idea is to get your blood sugar down and prevent insulin spikes. In reality you can modify it so that it works best for you, but you should go for 16 hour fasts at a minimum. A lot of new research shows that this is the best way for humans to lose weight. It's actually thought that this is how our evolutionary ancestors ate (they didn't eat 3 meals a day, but instead ate when they were able to get food.",
				p.getOriginalPostReplies().get(0).getReplyContent());
	}

}
