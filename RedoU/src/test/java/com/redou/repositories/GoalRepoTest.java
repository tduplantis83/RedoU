package com.redou.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.redou.entities.Goal;



@ExtendWith(SpringExtension.class)
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
