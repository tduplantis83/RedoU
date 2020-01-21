package com.redou.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.redou.entities.DailyCaloricIntake;



@RunWith(SpringRunner.class)
@SpringBootTest
class DailyCaloricIntakeRepoTest {
	
	@Autowired
	private DailyCaloricIntakeRepo repo;
	

	@Test
	@DisplayName("test FindByID")
	void test1() {
		DailyCaloricIntake d = repo.findById(1);
		assertEquals(500, d.getCaloriesThisMeal());
	}
	
	@Test
	@DisplayName("test findByUser_Id")
	void test2() {
		List<DailyCaloricIntake> d = repo.findByUser_Id(2);
		assertEquals(500, d.get(0).getCaloriesThisMeal());
	}

	@Test
	@DisplayName("test findByUser_UsernameIgnoreCase")
	void test3() {
		List<DailyCaloricIntake> d = repo.findByUser_UsernameIgnoreCase("TRAVisd");
		assertEquals(500, d.get(0).getCaloriesThisMeal());
	}
}
