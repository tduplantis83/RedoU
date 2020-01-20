package com.redou.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redou.entities.MealType;

public interface MealTypeRepo extends JpaRepository<MealType, Integer> {
	public MealType findById(int id);

	public MealType findByMealTypeName(String mealTypeName);

}
