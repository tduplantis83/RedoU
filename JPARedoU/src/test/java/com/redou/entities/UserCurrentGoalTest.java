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

class UserCurrentGoalTest {
		private static EntityManagerFactory emf;
		private EntityManager em;
		private UserCurrentGoal ucg;

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
			ucg = em.find(UserCurrentGoal.class, 1);
		}

		@AfterEach
		void tearDown() throws Exception {
			em.close();
			ucg = null;
		}

		@Test
		@DisplayName("Get User Current Goals Test")
		void test1() {
			assertEquals("travisd", ucg.getUser().getUsername());			
			assertEquals("Weight Loss", ucg.getGoal().getGoalName());			
		}

}
