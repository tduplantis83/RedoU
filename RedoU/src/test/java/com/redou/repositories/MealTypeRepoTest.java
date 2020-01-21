package com.redou.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.redou.entities.MealType;


@RunWith(SpringRunner.class)
@SpringBootTest
class MealTypeRepoTest {

	@Autowired
	private MealTypeRepo repo;
	
	@Test
	@DisplayName("test FindByID")
	void test1() {
		MealType mealType = repo.findById(1);
		assertEquals("Breakfast", mealType.getMealTypeName());
	}

	@Test
	@DisplayName("test findByMealTypeNameIgnoreCase")
	void test2() {
		MealType mealType = repo.findByMealTypeNameIgnoreCase("LUNch");
		assertEquals(2, mealType.getId());
	}

}
