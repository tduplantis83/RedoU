package com.redou.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.redou.entities.MealType;


@ExtendWith(SpringExtension.class)
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
