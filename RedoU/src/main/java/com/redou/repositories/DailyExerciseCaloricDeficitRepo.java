package com.redou.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redou.entities.DailyExerciseCaloricDeficit;

public interface DailyExerciseCaloricDeficitRepo extends JpaRepository<DailyExerciseCaloricDeficit, Integer> {
	public DailyExerciseCaloricDeficit findById(int id);

	public List<DailyExerciseCaloricDeficit> findByUser_Id(int userId);

	public List<DailyExerciseCaloricDeficit> findByUser_UsernameIgnoreCase(String username);

}
