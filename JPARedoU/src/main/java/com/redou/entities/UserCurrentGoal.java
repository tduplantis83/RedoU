package com.redou.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_current_goal")
public class UserCurrentGoal {
	
	//FIELDS
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="goal_id")
	private int goalId;
	
	private LocalDateTime dateCreated;
	
	
	//CONSTRUCTOR
	public UserCurrentGoal() {
		
	}


	//GETS & SETS
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getGoalId() {
		return goalId;
	}


	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}


	public LocalDateTime getDateCreated() {
		return dateCreated;
	}


	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}


	//HASH & EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + goalId;
		result = prime * result + id;
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
		UserCurrentGoal other = (UserCurrentGoal) obj;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (goalId != other.goalId)
			return false;
		if (id != other.id)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}


	//TO STRING
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserCurrentGoal [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", goalId=");
		builder.append(goalId);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append("]");
		return builder.toString();
	}
	

}
