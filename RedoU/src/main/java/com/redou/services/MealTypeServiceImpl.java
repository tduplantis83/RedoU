package com.redou.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redou.entities.MealType;
import com.redou.repositories.MealTypeRepo;

@Service
public class MealTypeServiceImpl implements MealTypeService{
	
	@Autowired
	private MealTypeRepo repo;

	@Override
	public MealType getMealTypeById(int id) {
		return repo.findById(id);
	}

	@Override
	public MealType getMealTypeByMealTypeName(String mealTypeName) {
		return repo.findByMealTypeNameIgnoreCase(mealTypeName);
	}
	
	@Override
	public List<MealType> getAllMealTypes() {
		return repo.findAll();
	}

	@Override
	public MealType createMealType(MealType mealType) {
		mealType.setDateCreated(LocalDate.now());
		mealType.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(mealType);
	}

	@Override
	public MealType updateMealType(MealType mealType, int id) {
		MealType toUpdate = repo.findById(mealType.getId());
		
		toUpdate.setMealTypeName(mealType.getMealTypeName());
		toUpdate.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(toUpdate);
	}

	@Override
	public boolean deleteMealType(int id) {
		try {
			MealType toDelete = repo.findById(id);

			repo.delete(toDelete);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	

}
