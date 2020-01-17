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

class DailyCaloricIntakeTest {


	private static EntityManagerFactory emf;
	private EntityManager em;
	private DailyCaloricIntake dci;

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
		dci = em.find(DailyCaloricIntake.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		dci = null;
	}

	@Test
	@DisplayName("Get Daily Caloric Intake Details Test")
	void test1() {
		assertEquals(500, dci.getTotalCaloriesEaten());
	}

	@Test
	@DisplayName("Get Daily Caloric Intake User Test")
	void test2() {
		assertEquals("travisd", dci.getUser().getUsername());
	}
}
