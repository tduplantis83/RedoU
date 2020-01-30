package com.redou.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;

	private String password;

	@Column(name="firstname")
	private String firstName;

	@Column(name="lastname")
	private String lastName;

	private LocalDate birthday;

	private String sex;

	private String email;

	private boolean enabled;

	private String role;

	@Column(name="datecreated")
	private LocalDate dateCreated;

	@Column(name="dateupdated")
	private LocalDate dateUpdated;

	@JsonIgnoreProperties({"user"})
	@OneToMany(mappedBy = "user")
	private List<UserCurrentGoal> userCurrentGoals;

	@JsonIgnoreProperties({"user"})
	@OneToMany(mappedBy = "user")
	private List<DailyCaloricIntake> userDailyCaloricIntakes;

	@JsonIgnoreProperties({"user"})
	@OneToMany(mappedBy = "user")
	private List<DailyExerciseCaloricDeficit> userDailyExerciseCaloricDeficits;

	@JsonIgnoreProperties({"user"})
	@OneToMany(mappedBy = "user")
	private List<Image> userImages;

	@JsonIgnoreProperties({"user"})
	@OneToMany(mappedBy = "user")
	private List<UserAvatar> userAvatars;

	@JsonIgnoreProperties({"user"})
	@OneToMany(mappedBy = "user")
	private List<BodyMeasurementMetric> userBodyMeasurementMetrics;

	@JsonIgnoreProperties({"id", "password", "birthday", "sex", "email", "enabled", "role", "dateCreated", "dateUpdated"})
	@OneToMany(mappedBy = "user")
	private List<Post> userPosts;

	@JsonIgnoreProperties({"id", "password", "birthday", "sex", "email", "enabled", "role", "dateCreated", "dateUpdated"})
	@OneToMany(mappedBy = "replyUser")
	private List<PostReply> userPostReplies;

	// CONSTRUCTOR
	public User() {

	}

	// GETS & SETS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public List<DailyCaloricIntake> getUserDailyCaloricIntakes() {
		return userDailyCaloricIntakes;
	}

	public void setUserDailyCaloricIntakes(List<DailyCaloricIntake> userDailyCaloricIntakes) {
		this.userDailyCaloricIntakes = userDailyCaloricIntakes;
	}

	public List<DailyExerciseCaloricDeficit> getUserDailyExerciseCaloricDeficits() {
		return userDailyExerciseCaloricDeficits;
	}

	public void setUserDailyExerciseCaloricDeficits(
			List<DailyExerciseCaloricDeficit> userDailyExerciseCaloricDeficits) {
		this.userDailyExerciseCaloricDeficits = userDailyExerciseCaloricDeficits;
	}

	public List<Image> getUserImages() {
		return userImages;
	}

	public void setUserImages(List<Image> userImages) {
		this.userImages = userImages;
	}

	public List<UserAvatar> getUserAvatars() {
		return userAvatars;
	}

	public void setUserAvatars(List<UserAvatar> userAvatars) {
		this.userAvatars = userAvatars;
	}

	public List<BodyMeasurementMetric> getUserBodyMeasurementMetrics() {
		return userBodyMeasurementMetrics;
	}

	public void setUserBodyMeasurementMetrics(List<BodyMeasurementMetric> userBodyMeasurementMetrics) {
		this.userBodyMeasurementMetrics = userBodyMeasurementMetrics;
	}

	public List<Post> getUserPosts() {
		return userPosts;
	}

	public void setUserPosts(List<Post> userPosts) {
		this.userPosts = userPosts;
	}

	public List<PostReply> getUserPostReplies() {
		return userPostReplies;
	}

	public void setUserPostReplies(List<PostReply> userPostReplies) {
		this.userPostReplies = userPostReplies;
	}

	// HASH & EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((dateUpdated == null) ? 0 : dateUpdated.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		result = prime * result + ((userAvatars == null) ? 0 : userAvatars.hashCode());
		result = prime * result + ((userBodyMeasurementMetrics == null) ? 0 : userBodyMeasurementMetrics.hashCode());
		result = prime * result + ((userCurrentGoals == null) ? 0 : userCurrentGoals.hashCode());
		result = prime * result + ((userDailyCaloricIntakes == null) ? 0 : userDailyCaloricIntakes.hashCode());
		result = prime * result
				+ ((userDailyExerciseCaloricDeficits == null) ? 0 : userDailyExerciseCaloricDeficits.hashCode());
		result = prime * result + ((userImages == null) ? 0 : userImages.hashCode());
		result = prime * result + ((userPostReplies == null) ? 0 : userPostReplies.hashCode());
		result = prime * result + ((userPosts == null) ? 0 : userPosts.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
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
		if (userBodyMeasurementMetrics == null) {
			if (other.userBodyMeasurementMetrics != null)
				return false;
		} else if (!userBodyMeasurementMetrics.equals(other.userBodyMeasurementMetrics))
			return false;
		if (userCurrentGoals == null) {
			if (other.userCurrentGoals != null)
				return false;
		} else if (!userCurrentGoals.equals(other.userCurrentGoals))
			return false;
		if (userDailyCaloricIntakes == null) {
			if (other.userDailyCaloricIntakes != null)
				return false;
		} else if (!userDailyCaloricIntakes.equals(other.userDailyCaloricIntakes))
			return false;
		if (userDailyExerciseCaloricDeficits == null) {
			if (other.userDailyExerciseCaloricDeficits != null)
				return false;
		} else if (!userDailyExerciseCaloricDeficits.equals(other.userDailyExerciseCaloricDeficits))
			return false;
		if (userImages == null) {
			if (other.userImages != null)
				return false;
		} else if (!userImages.equals(other.userImages))
			return false;
		if (userPostReplies == null) {
			if (other.userPostReplies != null)
				return false;
		} else if (!userPostReplies.equals(other.userPostReplies))
			return false;
		if (userPosts == null) {
			if (other.userPosts != null)
				return false;
		} else if (!userPosts.equals(other.userPosts))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	// TO STRING
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append(", sex=");
		builder.append(sex);
		builder.append(", email=");
		builder.append(email);
		builder.append(", enabled=");
		builder.append(enabled);
		builder.append(", role=");
		builder.append(role);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", dateUpdated=");
		builder.append(dateUpdated);
		builder.append("]");
		return builder.toString();
	}

}
