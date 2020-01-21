package com.redou.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.redou.entities.UserCurrentGoal;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserCurrentGoalRepoTest {
	
	@Autowired
	private UserCurrentGoalRepo repo;

	@Test
	@DisplayName("test FindByID")
	void test1() {
		UserCurrentGoal goal = repo.findById(2);
		assertEquals("Weight Loss", goal.getGoal().getGoalName());
	}

}
