package com.redou.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "meal_type")
public class MealType {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String mealTypeName;

	private LocalDate dateCreated;

	private LocalDate dateUpdated;

	@OneToMany(mappedBy = "mealType")
	private List<DailyCaloricIntake> dailyCaloricIntakesMealTypes;

	// CONSTRUCTOR
	public MealType() {

	}

	// GETS & SETS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMealTypeName() {
		return mealTypeName;
	}

	public void setMealTypeName(String mealTypeName) {
		this.mealTypeName = mealTypeName;
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

	public List<DailyCaloricIntake> getDailyCaloricIntakesMealTypes() {
		return dailyCaloricIntakesMealTypes;
	}

	public void setDailyCaloricIntakesMealTypes(List<DailyCaloricIntake> dailyCaloricIntakesMealType) {
		this.dailyCaloricIntakesMealTypes = dailyCaloricIntakesMealType;
	}

	// HASH & EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dailyCaloricIntakesMealTypes == null) ? 0 : dailyCaloricIntakesMealTypes.hashCode());
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((dateUpdated == null) ? 0 : dateUpdated.hashCode());
		result = prime * result + id;
		result = prime * result + ((mealTypeName == null) ? 0 : mealTypeName.hashCode());
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
		MealType other = (MealType) obj;
		if (dailyCaloricIntakesMealTypes == null) {
			if (other.dailyCaloricIntakesMealTypes != null)
				return false;
		} else if (!dailyCaloricIntakesMealTypes.equals(other.dailyCaloricIntakesMealTypes))
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
		if (mealTypeName == null) {
			if (other.mealTypeName != null)
				return false;
		} else if (!mealTypeName.equals(other.mealTypeName))
			return false;
		return true;
	}

	// TO STRING
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MealType [id=");
		builder.append(id);
		builder.append(", mealTypeName=");
		builder.append(mealTypeName);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", dateUpdated=");
		builder.append(dateUpdated);
		builder.append("]");
		return builder.toString();
	}

}
