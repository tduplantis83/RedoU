package com.redou.services;

import com.redou.entities.Goal;

public interface GoalService {
	public Goal getGoalById(int id);

	public Goal getGoalByGoalName(String goalName);
}
