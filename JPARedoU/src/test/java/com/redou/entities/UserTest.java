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
		assertEquals("1983-07-01", user.getBirthday().toString());
		assertEquals("M", user.getSex());
		assertEquals("Duplantis", user.getLastName());
		assertEquals("tduplantis83@gmail.com", user.getEmail());
		assertTrue(user.isEnabled());
		assertEquals("user", user.getRole());
		assertEquals("2020-01-16", user.getDateCreated().toString());
		assertEquals("2020-01-16", user.getDateUpdated().toString());
		
	}

	@Test
	@DisplayName("Get User to Goal Test")
	void test2() {
		assertEquals(2, user.getUserCurrentGoals().size());
		assertEquals(1, user.getUserCurrentGoals().get(0).getGoal().getId());
		assertEquals("Weight Loss", user.getUserCurrentGoals().get(0).getGoal().getGoalName());
		
	}
	
	@Test
	@DisplayName("Get User to Daily Caloric Intake Test")
	void test3() {
		assertEquals(4, user.getUserDailyCaloricIntakes().size());
		assertEquals(500, user.getUserDailyCaloricIntakes().get(0).getCaloriesThisMeal());
		assertEquals("Dinner", user.getUserDailyCaloricIntakes().get(0).getMealType().getMealTypeName());
		
	}

	@Test
	@DisplayName("Get User to Daily Exercise Caloric Deficit Test")
	void test4() {
		assertEquals(4, user.getUserDailyExerciseCaloricDeficits().size());
		assertEquals(1928, user.getUserDailyExerciseCaloricDeficits().get(0).getTotalCaloriesBurned());
		assertEquals("Basal Metabolic Rate", user.getUserDailyExerciseCaloricDeficits().get(0).getActivityDescription());
		
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
		assertEquals(2, user.getUserBodyMeasurementMetrics().size());
		assertEquals(1854, user.getUserBodyMeasurementMetrics().get(0).getHeightMM());
		assertEquals(94.26, user.getUserBodyMeasurementMetrics().get(0).getWeightKg());
		assertEquals(1090, user.getUserBodyMeasurementMetrics().get(0).getWaistMM());
		
	}
	
	@Test
	@DisplayName("Get User to Post Test")
	void test7() {
		User u2 = em.find(User.class, 3);
		assertEquals(1, u2.getUserPosts().size());		
		assertEquals("Intermittent Fasting", u2.getUserPosts().get(0).getTitle());
		u2 = null;
	}
	
	@Test
	@DisplayName("Get User to Post Topic Test")
	void test8() {
		User u2 = em.find(User.class, 3);
		assertEquals("Diet", u2.getUserPosts().get(0).getPostTopic().getTopicName());
		u2 = null;		
	}
	
	@Test
	@DisplayName("Get User to Post Reply Test")
	void test9() {
		assertEquals(1, user.getUserPostReplies().size());
		assertEquals("There are several different types of intermittent fasting including: 16 hours fasting with 8 hours eating, One Meal A Day (OMAD). The general idea is to get your blood sugar down and prevent insulin spikes. In reality you can modify it so that it works best for you, but you should go for 16 hour fasts at a minimum. A lot of new research shows that this is the best way for humans to lose weight. It's actually thought that this is how our evolutionary ancestors ate (they didn't eat 3 meals a day, but instead ate when they were able to get food.", user.getUserPostReplies().get(0).getReplyContent());
		
	}
	
	@Test
	@DisplayName("Get User to Avatar Test")
	void test10() {
		assertEquals(5, user.getUserAvatars().size());
		assertEquals("https://i.imgur.com/uJaRmvQ.jpg", user.getUserAvatars().get(0).getAvatarUrl());
		assertEquals("Thin", user.getUserAvatars().get(0).getBodyType());
	}
	
	
}
