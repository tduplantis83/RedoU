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


class UserTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	@DisplayName("Get User Details Test")
	void test1() {
		assertEquals("travisd", user.getUsername());
		assertEquals("Travis", user.getFirstName());
		assertEquals("Duplantis", user.getLastName());
		assertEquals("tduplantis83@gmail.com", user.getEmail());
		assertTrue(user.isEnabled());
		assertEquals("user", user.getRole());
		assertEquals("2020-01-16T00:00", user.getDateCreated().toString());
		assertEquals("2020-01-16T00:00", user.getDateUpdated().toString());
		
	}

	@Test
	@DisplayName("Get User to Goal Test")
	void test2() {
		assertEquals(1, user.getUserCurrentGoals().size());
		assertEquals(1, user.getUserCurrentGoals().get(0).getGoal().getId());
		assertEquals("Weight Loss", user.getUserCurrentGoals().get(0).getGoal().getGoalName());
		
	}
	
	@Test
	@DisplayName("Get User to Daily Caloric Intake Test")
	void test3() {
		assertEquals(0, user.getUserDailyCaloricIntakes().size());
		
	}

	@Test
	@DisplayName("Get User to Daily Exercise Caloric Deficit Test")
	void test4() {
		assertEquals(0, user.getUserDailyExerciseCaloricDeficits().size());
		
	}
	
	@Test
	@DisplayName("Get User to Image Test")
	void test5() {
		assertEquals(2, user.getUserImages().size());
		assertEquals("https://i.imgur.com/puVjtA9.jpg", user.getUserImages().get(0).getImageUrl());
		assertEquals("https://i.imgur.com/zSACF2B.jpg", user.getUserImages().get(1).getImageUrl());
		
	}
	
	@Test
	@DisplayName("Get User to Body Measurement Metric Test")
	void test6() {
		assertEquals(1, user.getUserBodyMeasurementMetrics().size());
		assertEquals(1854, user.getUserBodyMeasurementMetrics().get(0).getHeightMM());
		assertEquals(94.26, user.getUserBodyMeasurementMetrics().get(0).getWeightKg());
		assertEquals(1090, user.getUserBodyMeasurementMetrics().get(0).getWaistMM());
		
	}
}
