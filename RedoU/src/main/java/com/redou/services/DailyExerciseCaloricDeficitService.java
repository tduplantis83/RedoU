package com.redou.services;

import java.util.List;

import com.redou.entities.DailyExerciseCaloricDeficit;

public interface DailyExerciseCaloricDeficitService {
	public DailyExerciseCaloricDeficit getDailyExerciseCaloricDeficitById(int id);

	public List<DailyExerciseCaloricDeficit> getDailyExerciseCaloricDeficitByUserId(int userId);

	public List<DailyExerciseCaloricDeficit> getDailyExerciseCaloricDeficitByUsername(String username);

}
