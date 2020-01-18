package com.redou.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Goal {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String goalName;

	private LocalDate dateCreated;

	private LocalDate dateUpdated;

	@OneToMany(mappedBy = "goal")
	private List<UserCurrentGoal> userCurrentGoals;

	// CONSTRUCTOR
	public Goal() {

	}

	// GETS & SETS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGoalName() {
		return goalName;
	}

	public void setGoalName(String goalName) {
		this.goalName = goalName;
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

	public List<UserCurrentGoal> getUserCurrentGoals() {
		return userCurrentGoals;
	}

	public void setUserCurrentGoals(List<UserCurrentGoal> userCurrentGoals) {
		this.userCurrentGoals = userCurrentGoals;
	}

	// HASH & EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((dateUpdated == null) ? 0 : dateUpdated.hashCode());
		result = prime * result + ((goalName == null) ? 0 : goalName.hashCode());
		result = prime * result + id;
		result = prime * result + ((userCurrentGoals == null) ? 0 : userCurrentGoals.hashCode());
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
		Goal other = (Goal) obj;
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
		if (goalName == null) {
			if (other.goalName != null)
				return false;
		} else if (!goalName.equals(other.goalName))
			return false;
		if (id != other.id)
			return false;
		if (userCurrentGoals == null) {
			if (other.userCurrentGoals != null)
				return false;
		} else if (!userCurrentGoals.equals(other.userCurrentGoals))
			return false;
		return true;
	}

	// TO STRING
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Goal [id=");
		builder.append(id);
		builder.append(", goalName=");
		builder.append(goalName);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", dateUpdated=");
		builder.append(dateUpdated);
		builder.append("]");
		return builder.toString();
	}

}
