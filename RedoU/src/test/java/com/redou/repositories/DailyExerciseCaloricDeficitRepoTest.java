package com.redou.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.redou.entities.DailyExerciseCaloricDeficit;



@RunWith(SpringRunner.class)
@SpringBootTest
class DailyExerciseCaloricDeficitRepoTest {

	@Autowired
	private DailyExerciseCaloricDeficitRepo repo;
	
	@Test
	@DisplayName("test FindByID")
	void test1() {
		DailyExerciseCaloricDeficit d = repo.findById(1);
		assertEquals(1928, d.getTotalCaloriesBurned());
	}
	
	@Test
	@DisplayName("test findByUser_Id")
	void test2() {
		List<DailyExerciseCaloricDeficit> d = repo.findByUser_Id(2);
		assertEquals(1928, d.get(0).getTotalCaloriesBurned());
	}

	@Test
	@DisplayName("test findByUser_UsernameIgnoreCase")
	void test3() {
		List<DailyExerciseCaloricDeficit> d = repo.findByUser_UsernameIgnoreCase("trAVISd");
		assertEquals(1928, d.get(0).getTotalCaloriesBurned());
	}
}
