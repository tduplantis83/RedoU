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

class GoalTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Goal g;

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
		g = em.find(Goal.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		g = null;
	}

	@Test
	@DisplayName("Get Goal Test")
	void test1() {
		assertEquals("Weight Loss", g.getGoalName());
	}
	
	@Test
	@DisplayName("Get Goal to User Test")
	void test2() {
		assertEquals("travisd", g.getUserCurrentGoals().get(0).getUser().getUsername());
	}

}
