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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "daily_caloric_intake")
public class DailyCaloricIntake {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnoreProperties({"dailyCaloricIntakesMealTypes"})
	@ManyToOne
	@JoinColumn(name = "mealtype_id")
	private MealType mealType;

	@Column(name="datecreated")
	private LocalDate dateCreated;

	@Column(name="dateupdated")
	private LocalDate dateUpdated;

	@Column(name="caloriesthismeal")
	private int caloriesThisMeal;

	@Column(name="mealdescription")
	private String mealDescription;

	@JsonIgnoreProperties({"userCurrentGoals", "userDailyCaloricIntakes", "userDailyExerciseCaloricDeficits", "userImages", "userAvatars", "userBodyMeasurementMetrics", "userPosts", "userPostReplies"})
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	// CONSTRUCTOR
	public DailyCaloricIntake() {

	}

	// GETS & SETS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MealType getMealType() {
		return mealType;
	}

	public void setMealType(MealType mealType) {
		this.mealType = mealType;
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

	public int getCaloriesThisMeal() {
		return caloriesThisMeal;
	}

	public void setCaloriesThisMeal(int caloriesThisMeal) {
		this.caloriesThisMeal = caloriesThisMeal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMealDescription() {
		return mealDescription;
	}

	public void setMealDescription(String mealDescription) {
		this.mealDescription = mealDescription;
	}

	// HASH & EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + caloriesThisMeal;
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((dateUpdated == null) ? 0 : dateUpdated.hashCode());
		result = prime * result + id;
		result = prime * result + ((mealDescription == null) ? 0 : mealDescription.hashCode());
		result = prime * result + ((mealType == null) ? 0 : mealType.hashCode());
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
		DailyCaloricIntake other = (DailyCaloricIntake) obj;
		if (caloriesThisMeal != other.caloriesThisMeal)
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
		if (mealDescription == null) {
			if (other.mealDescription != null)
				return false;
		} else if (!mealDescription.equals(other.mealDescription))
			return false;
		if (mealType == null) {
			if (other.mealType != null)
				return false;
		} else if (!mealType.equals(other.mealType))
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
		builder.append("DailyCaloricIntake [id=");
		builder.append(id);
		builder.append(", mealType=");
		builder.append(mealType);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", dateUpdated=");
		builder.append(dateUpdated);
		builder.append(", caloriesThisMeal=");
		builder.append(caloriesThisMeal);
		builder.append(", mealDescription=");
		builder.append(mealDescription);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}

}
