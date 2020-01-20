package com.redou.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redou.entities.Goal;

public interface GoalRepo extends JpaRepository<Goal, Integer> {
	public Goal findById(int id);

	public Goal findByGoalNameIgnoreCase(String goalName);

}
