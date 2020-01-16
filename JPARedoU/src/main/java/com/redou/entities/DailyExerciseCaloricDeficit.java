package com.redou.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="daily_exercise_caloric_deficit")
public class DailyExerciseCaloricDeficit {
	
	//FIELDS
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private LocalDate dateCreated;
	
	private int totalCaloriesBurned;
	
	@Column(name="user_id")
	private int userId;
	
	
	//CONSTRUCTOR
	public DailyExerciseCaloricDeficit() {
		
	}

	
	//GETS & SETS
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public LocalDate getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}


	public int getTotalCaloriesBurned() {
		return totalCaloriesBurned;
	}


	public void setTotalCaloriesBurned(int totalCaloriesBurned) {
		this.totalCaloriesBurned = totalCaloriesBurned;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	//HASH & EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + id;
		result = prime * result + totalCaloriesBurned;
		result = prime * result + userId;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyExerciseCaloricDeficit other = (DailyExerciseCaloricDeficit) obj;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (id != other.id)
			return false;
		if (totalCaloriesBurned != other.totalCaloriesBurned)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}


	//TO STRING
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DailyExerciseCaloricDeficit [id=");
		builder.append(id);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", totalCaloriesBurned=");
		builder.append(totalCaloriesBurned);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}


}
