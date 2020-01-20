package com.redou.services;

import java.util.List;

import com.redou.entities.UserCurrentGoal;

public interface UserCurrentGoalService {
	public UserCurrentGoal getUserCurrentGoalById(int id);

	public List<UserCurrentGoal> getUserCurrentGoalByUserId(int userId);

	public List<UserCurrentGoal> getUserCurrentGoalByUsername(String username);
}
