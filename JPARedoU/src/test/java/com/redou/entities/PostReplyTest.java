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

class PostReplyTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private PostReply pr;

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
		pr = em.find(PostReply.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		pr = null;
	}

	@Test
	@DisplayName("Get Post Reply Test")
	void test1() {
		assertEquals(
				"There are several different types of intermittent fasting including: 16 hours fasting with 8 hours eating, One Meal A Day (OMAD). The general idea is to get your blood sugar down and prevent insulin spikes. In reality you can modify it so that it works best for you, but you should go for 16 hour fasts at a minimum. A lot of new research shows that this is the best way for humans to lose weight. It's actually thought that this is how our evolutionary ancestors ate (they didn't eat 3 meals a day, but instead ate when they were able to get food.",
				pr.getReplyContent());
	}

	@Test
	@DisplayName("Get Post Reply to Original Post Test")
	void test2() {
		assertEquals("Intermittent Fasting", pr.getOriginalPost().getTitle());
	}
	
	@Test
	@DisplayName("Get Post Reply to Reply User Test")
	void test3() {
		assertEquals("travisd", pr.getReplyUser().getUsername());
	}
	
	@Test
	@DisplayName("Get Post Reply to Post Reply Test")
	void test4() {
		assertEquals(1, pr.getRepliesToPostReply().size());
		assertEquals("Which intermittent fasting method is the best for losing weight quickly, and why?", pr.getRepliesToPostReply().get(0).getReplyContent());
	}

}
