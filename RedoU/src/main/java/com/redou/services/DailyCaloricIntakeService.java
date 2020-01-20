package com.redou.services;

import java.util.List;

import com.redou.entities.DailyCaloricIntake;

public interface DailyCaloricIntakeService {
	public DailyCaloricIntake getDailyCaloricIntakeById(int id);

	public List<DailyCaloricIntake> getDailyCaloricIntakeByUserId(int userId);

	public List<DailyCaloricIntake> getDailyCaloricIntakeByUsername(String username);
}
