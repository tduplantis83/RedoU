package com.redou.services;

import com.redou.entities.MealType;

public interface MealTypeService {
	public MealType getMealTypeById(int id);

	public MealType getMealTypeByMealTypeName(String mealTypeName);
}
