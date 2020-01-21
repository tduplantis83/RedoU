package com.redou.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.redou.entities.Goal;



@RunWith(SpringRunner.class)
@SpringBootTest
class GoalRepoTest {
	@Autowired
	private GoalRepo repo;
	
	@Test
	@DisplayName("test FindByID")
	void test1() {
		Goal g = repo.findById(1);
		assertEquals("Weight Loss", g.getGoalName());
	}
	
	@Test
	@DisplayName("test findByGoalNameIgnoreCase")
	void test2() {
		Goal g = repo.findByGoalNameIgnoreCase("MUSCle builDING");
		assertEquals(2, g.getId());
	}

}
