package com.redou.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redou.entities.UserCurrentGoal;
import com.redou.repositories.UserCurrentGoalRepo;

@Service
public class UserCurrentGoalServiceImpl implements UserCurrentGoalService{
	
	@Autowired
	private UserCurrentGoalRepo repo;

	@Override
	public UserCurrentGoal getUserCurrentGoalById(int id) {
		return repo.findById(id);
	}

	@Override
	public List<UserCurrentGoal> getUserCurrentGoalByUserId(int userId) {
		return repo.findByUser_Id(userId);
	}

	@Override
	public List<UserCurrentGoal> getUserCurrentGoalByUsername(String username) {
		return repo.findByUser_UsernameIgnoreCase(username);
	}

	@Override
	public UserCurrentGoal createUserCurrentGoal(UserCurrentGoal goal) {
		goal.setDateCreated(LocalDate.now());
		goal.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(goal);
	}

	@Override
	public UserCurrentGoal updateUserCurrentGoal(UserCurrentGoal goal) {
		UserCurrentGoal toUpdate = repo.findById(goal.getId());
		
		toUpdate.setUser(goal.getUser());
		toUpdate.setGoal(goal.getGoal());
		toUpdate.setEnabled(goal.getEnabled());
		toUpdate.setDateCreated(goal.getDateCreated());
		toUpdate.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(toUpdate);
	}

	@Override
	public boolean deleteUserCurrentGoal(int id) {
		try {
			UserCurrentGoal toDelete = repo.findById(id);

			repo.delete(toDelete);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
