package com.redou.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "daily_exercise_caloric_deficit")
public class DailyExerciseCaloricDeficit {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="datecreated")
	private LocalDate dateCreated;

	@Column(name="dateupdated")
	private LocalDate dateUpdated;

	@Column(name="totalcaloriesburned")
	private int totalCaloriesBurned;

	@Column(name="activitydescription")
	private String activityDescription;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	// CONSTRUCTOR
	public DailyExerciseCaloricDeficit() {

	}

	// GETS & SETS
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

	public LocalDate getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDate dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public int getTotalCaloriesBurned() {
		return totalCaloriesBurned;
	}

	public void setTotalCaloriesBurned(int totalCaloriesBurned) {
		this.totalCaloriesBurned = totalCaloriesBurned;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	// HASH & EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityDescription == null) ? 0 : activityDescription.hashCode());
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((dateUpdated == null) ? 0 : dateUpdated.hashCode());
		result = prime * result + id;
		result = prime * result + totalCaloriesBurned;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (activityDescription == null) {
			if (other.activityDescription != null)
				return false;
		} else if (!activityDescription.equals(other.activityDescription))
			return false;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (dateUpdated == null) {
			if (other.dateUpdated != null)
				return false;
		} else if (!dateUpdated.equals(other.dateUpdated))
			return false;
		if (id != other.id)
			return false;
		if (totalCaloriesBurned != other.totalCaloriesBurned)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	// TO STRING
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DailyExerciseCaloricDeficit [id=");
		builder.append(id);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", dateUpdated=");
		builder.append(dateUpdated);
		builder.append(", totalCaloriesBurned=");
		builder.append(totalCaloriesBurned);
		builder.append(", activityDescription=");
		builder.append(activityDescription);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}

}
