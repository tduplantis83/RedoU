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

class MealTypeTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private MealType mt;

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
		mt = em.find(MealType.class, 3);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		mt = null;
	}

	@Test
	@DisplayName("Get Meal Type Details Test")
	void test1() {
		assertEquals("Dinner", mt.getMealTypeName());
	}
	
	@Test
	@DisplayName("Get Meal Type to Daily Caloric Intake Test")
	void test2() {
		assertEquals(500, mt.getDailyCaloricIntakesMealTypes().get(0).getCaloriesThisMeal());
	}

}
