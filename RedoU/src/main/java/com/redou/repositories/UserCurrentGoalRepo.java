package com.redou.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redou.entities.UserCurrentGoal;

public interface UserCurrentGoalRepo extends JpaRepository<UserCurrentGoal, Integer> {
	public UserCurrentGoal findById(int id);

	public List<UserCurrentGoal> findByUser_Id(int userId);

	public List<UserCurrentGoal> findByUser_UsernameIgnoreCase(String username);

}
