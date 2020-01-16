package com.redou.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Goal {
	
	//FIELDS
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String goalName;
	
	private LocalDate dateCreated;
	
	
	//CONSTRUCTOR
	public Goal() {
		
	}


	//GETS & SETS
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


	//HASH & EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((goalName == null) ? 0 : goalName.hashCode());
		result = prime * result + id;
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
		if (goalName == null) {
			if (other.goalName != null)
				return false;
		} else if (!goalName.equals(other.goalName))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	
	//TO STRING
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Goal [id=");
		builder.append(id);
		builder.append(", goalName=");
		builder.append(goalName);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append("]");
		return builder.toString();
	}


}
