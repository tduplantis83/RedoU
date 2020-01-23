package com.redou.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redou.entities.DailyExerciseCaloricDeficit;
import com.redou.repositories.DailyExerciseCaloricDeficitRepo;

@Service
public class DailyExerciseCaloricDeficitServiceImpl implements DailyExerciseCaloricDeficitService {
	
	@Autowired
	private DailyExerciseCaloricDeficitRepo repo;

	@Override
	public DailyExerciseCaloricDeficit getDailyExerciseCaloricDeficitById(int id) {
		return repo.findById(id);
	}

	@Override
	public List<DailyExerciseCaloricDeficit> getDailyExerciseCaloricDeficitByUserId(int userId) {
		return repo.findByUser_Id(userId);
	}

	@Override
	public List<DailyExerciseCaloricDeficit> getDailyExerciseCaloricDeficitByUsername(String username) {
		return repo.findByUser_UsernameIgnoreCase(username);
	}

	@Override
	public DailyExerciseCaloricDeficit createDailyExerciseCaloricDeficit(DailyExerciseCaloricDeficit deficit) {
		deficit.setDateCreated(LocalDate.now());
		deficit.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(deficit);
	}

	@Override
	public DailyExerciseCaloricDeficit updateDailyExerciseCaloricDeficit(DailyExerciseCaloricDeficit deficit, int id) {
		DailyExerciseCaloricDeficit toUpdate = repo.findById(id);
		
		toUpdate.setUser(deficit.getUser());
		toUpdate.setTotalCaloriesBurned(deficit.getTotalCaloriesBurned());
		toUpdate.setActivityDescription(deficit.getActivityDescription());
		//allow update of dateCreated incase the wrong date was entered when it was created
		toUpdate.setDateCreated(deficit.getDateCreated());
		toUpdate.setDateUpdated(LocalDate.now());

		return repo.saveAndFlush(toUpdate);
	}

	@Override
	public boolean deleteDailyExerciseCaloricDeficit(int id) {
		try {
			DailyExerciseCaloricDeficit toDelete = repo.findById(id);

			repo.delete(toDelete);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
