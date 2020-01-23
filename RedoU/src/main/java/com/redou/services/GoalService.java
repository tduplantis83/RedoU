package com.redou.services;

import java.util.List;

import com.redou.entities.Goal;

public interface GoalService {
	public Goal getGoalById(int id);

	public Goal getGoalByGoalName(String goalName);
	
	public List<Goal> getAllGoals();
	
	public Goal createGoal(Goal goal);

	public Goal updateGoal(Goal goal, int id);
	
	public boolean deleteGoal(int id);
}
