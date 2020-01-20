package com.redou.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redou.entities.DailyCaloricIntake;

public interface DailyCaloricIntakeRepo extends JpaRepository<DailyCaloricIntake, Integer> {
	public DailyCaloricIntake findById(int id);

	public List<DailyCaloricIntake> findByUser_id(int userId);

	public List<DailyCaloricIntake> findByUser_Username(String username);
}
