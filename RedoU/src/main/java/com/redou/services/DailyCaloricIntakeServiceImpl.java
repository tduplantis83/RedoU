package com.redou.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redou.entities.DailyCaloricIntake;
import com.redou.repositories.DailyCaloricIntakeRepo;

@Service
public class DailyCaloricIntakeServiceImpl implements DailyCaloricIntakeService {

	@Autowired
	private DailyCaloricIntakeRepo repo;
	
	@Override
	public DailyCaloricIntake getDailyCaloricIntakeById(int id) {
		return repo.findById(id);
	}

	@Override
	public List<DailyCaloricIntake> getDailyCaloricIntakeByUserId(int userId) {
		return repo.findByUser_Id(userId);
	}

	@Override
	public List<DailyCaloricIntake> getDailyCaloricIntakeByUsername(String username) {
		return repo.findByUser_UsernameIgnoreCase(username);
	}

	@Override
	public DailyCaloricIntake createDailyCaloricIntake(DailyCaloricIntake intake) {
		if(intake.getDateCreated() == null) {
			intake.setDateCreated(LocalDate.now());
		}
		intake.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(intake);
	}

	@Override
	public DailyCaloricIntake updateDailyCaloricIntake(DailyCaloricIntake intake, int id) {
		DailyCaloricIntake toUpdate = repo.findById(id);
		
		if(intake.getUser() != null) {
			toUpdate.setUser(intake.getUser());
		}
		if(intake.getMealType() != null) {
			toUpdate.setMealType(intake.getMealType());
		}
		if(intake.getCaloriesThisMeal() != 0) {
			toUpdate.setCaloriesThisMeal(intake.getCaloriesThisMeal());
		}
		if(intake.getMealDescription() != null) {
			toUpdate.setMealDescription(intake.getMealDescription());
		}
		//allow update of dateCreated incase wrong date was used when recored was created
		if(intake.getDateCreated() != null) {
			toUpdate.setDateCreated(intake.getDateCreated());
		}
		toUpdate.setDateUpdated(LocalDate.now());

		return repo.saveAndFlush(toUpdate);
	}

	@Override
	public boolean deleteDailyCaloricIntake(int id) {
		try {
			DailyCaloricIntake toDelete = repo.findById(id);

			repo.delete(toDelete);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
