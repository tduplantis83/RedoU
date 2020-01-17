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

class DailyExerciseCaloricDeficitTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private DailyExerciseCaloricDeficit decd;

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
		decd = em.find(DailyExerciseCaloricDeficit.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		decd = null;
	}

	@Test
	@DisplayName("Get Daily Exercise Caloric Deficit Details Test")
	void test1() {
		assertEquals(1943, decd.getTotalCaloriesBurned());
	}
	
	@Test
	@DisplayName("Get Daily Exercise Caloric Deficit to User Test")
	void test2() {
		assertEquals("travisd", decd.getUser().getUsername());
	}

}
