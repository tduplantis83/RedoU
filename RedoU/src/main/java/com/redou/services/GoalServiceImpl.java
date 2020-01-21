package com.redou.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redou.entities.Goal;
import com.redou.repositories.GoalRepo;

@Service
public class GoalServiceImpl implements GoalService{
	
	@Autowired
	private GoalRepo repo;

	@Override
	public Goal getGoalById(int id) {
		return repo.findById(id);
	}

	@Override
	public Goal getGoalByGoalName(String goalName) {
		return repo.findByGoalNameIgnoreCase(goalName);
	}

	@Override
	public Goal createGoal(Goal goal) {
		goal.setDateCreated(LocalDate.now());
		goal.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(goal);
	}

	@Override
	public Goal updateGoal(Goal goal) {
		Goal toUpdate = repo.findById(goal.getId());
		
		toUpdate.setGoalName(goal.getGoalName());
		toUpdate.setDateCreated(goal.getDateCreated());
		toUpdate.setDateUpdated(LocalDate.now());

		return repo.saveAndFlush(toUpdate);
	}

	@Override
	public boolean deleteGoal(int id) {
		try {
			Goal toDelete = repo.findById(id);

			repo.delete(toDelete);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
