package com.redou.services;

import com.redou.entities.MealType;

public interface MealTypeService {
	public MealType getMealTypeById(int id);

	public MealType getMealTypeByMealTypeName(String mealTypeName);
	
	public MealType createMealType(MealType mealType);

	public MealType updateMealType(MealType mealType);
	
	public boolean deleteMealType(int id);
}
