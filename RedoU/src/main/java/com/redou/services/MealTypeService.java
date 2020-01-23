package com.redou.services;

import java.util.List;

import com.redou.entities.MealType;

public interface MealTypeService {
	public MealType getMealTypeById(int id);

	public MealType getMealTypeByMealTypeName(String mealTypeName);
	
	public List<MealType> getAllMealTypes();
	
	public MealType createMealType(MealType mealType);

	public MealType updateMealType(MealType mealType, int id);
	
	public boolean deleteMealType(int id);
}
