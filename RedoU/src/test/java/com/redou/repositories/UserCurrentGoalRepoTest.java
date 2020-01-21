package com.redou.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
	
	@Test
	@DisplayName("test findByUser_Id")
	void test2() {
		List<UserCurrentGoal> goals = repo.findByUser_Id(2);
		assertEquals("Weight Loss", goals.get(0).getGoal().getGoalName());
	}

	@Test
	@DisplayName("test findByUser_UsernameIgnoreCase")
	void test3() {
		List<UserCurrentGoal> goals = repo.findByUser_UsernameIgnoreCase("travisD");
		assertEquals("Weight Loss", goals.get(0).getGoal().getGoalName());
	}
}
