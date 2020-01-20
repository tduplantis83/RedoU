package com.redou.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Avatar {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int avatarGroup;

	private String sex;

	private String bodyType;

	private String avatarUrl;

	private LocalDate dateCreated;

	private LocalDate dateUpdated;

	@OneToMany(mappedBy = "avatar")
	private List<UserAvatar> userAvatars;
	

	// CONSTRUCTOR
	public Avatar() {

	}

	// GETS & SETS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAvatarGroup() {
		return avatarGroup;
	}

	public void setAvatarGroup(int avatarGroup) {
		this.avatarGroup = avatarGroup;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
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

	public List<UserAvatar> getUserAvatars() {
		return userAvatars;
	}

	public void setUserCurrentAvatars(List<UserAvatar> userAvatars) {
		this.userAvatars = userAvatars;
	}

	// HASH & EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + avatarGroup;
		result = prime * result + ((avatarUrl == null) ? 0 : avatarUrl.hashCode());
		result = prime * result + ((bodyType == null) ? 0 : bodyType.hashCode());
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((dateUpdated == null) ? 0 : dateUpdated.hashCode());
		result = prime * result + id;
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((userAvatars == null) ? 0 : userAvatars.hashCode());
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
		Avatar other = (Avatar) obj;
		if (avatarGroup != other.avatarGroup)
			return false;
		if (avatarUrl == null) {
			if (other.avatarUrl != null)
				return false;
		} else if (!avatarUrl.equals(other.avatarUrl))
			return false;
		if (bodyType == null) {
			if (other.bodyType != null)
				return false;
		} else if (!bodyType.equals(other.bodyType))
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
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		if (userAvatars == null) {
			if (other.userAvatars != null)
				return false;
		} else if (!userAvatars.equals(other.userAvatars))
			return false;
		return true;
	}

	// TO STRING
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Avatar [id=");
		builder.append(id);
		builder.append(", avatarGroup=");
		builder.append(avatarGroup);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", bodyType=");
		builder.append(bodyType);
		builder.append(", avatarUrl=");
		builder.append(avatarUrl);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", dateUpdated=");
		builder.append(dateUpdated);
		builder.append("]");
		return builder.toString();
	}

}
