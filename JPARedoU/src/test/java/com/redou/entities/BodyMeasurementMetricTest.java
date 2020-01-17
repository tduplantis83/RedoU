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

class BodyMeasurementMetricTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private BodyMeasurementMetric bmm;

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
		bmm = em.find(BodyMeasurementMetric.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		bmm = null;
	}

	@Test
	@DisplayName("Get Body Measurment Metric Details Test")
	void test1() {
		assertEquals(1090, bmm.getWaistMM());
	}

	@Test
	@DisplayName("Get Body Measurment Metric to User Test")
	void test2() {
		assertEquals("tduplantis83@gmail.com", bmm.getUser().getEmail());
	}

}
