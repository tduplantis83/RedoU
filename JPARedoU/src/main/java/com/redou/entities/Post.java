package com.redou.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Post {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "posttopic_id")
	private PostTopic postTopic;

	private String title;

	private String content;

	@Column(name="datecreated")
	private LocalDate dateCreated;

	@Column(name="dateupdated")
	private LocalDate dateUpdated;

	@OneToMany(mappedBy = "originalPost")
	private List<PostReply> originalPostReplies;

	// CONSTRUCTOR
	public Post() {

	}

	// GETS & SETS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PostTopic getPostTopic() {
		return postTopic;
	}

	public void setPostTopic(PostTopic postTopic) {
		this.postTopic = postTopic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public List<PostReply> getOriginalPostReplies() {
		return originalPostReplies;
	}

	public void setOriginalPostReplies(List<PostReply> originalPostReplies) {
		this.originalPostReplies = originalPostReplies;
	}

	// HASH & EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((dateUpdated == null) ? 0 : dateUpdated.hashCode());
		result = prime * result + id;
		result = prime * result + ((originalPostReplies == null) ? 0 : originalPostReplies.hashCode());
		result = prime * result + ((postTopic == null) ? 0 : postTopic.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Post other = (Post) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
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
		if (originalPostReplies == null) {
			if (other.originalPostReplies != null)
				return false;
		} else if (!originalPostReplies.equals(other.originalPostReplies))
			return false;
		if (postTopic == null) {
			if (other.postTopic != null)
				return false;
		} else if (!postTopic.equals(other.postTopic))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
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
		builder.append("Post [id=");
		builder.append(id);
		builder.append(", user=");
		builder.append(user);
		builder.append(", postTopic=");
		builder.append(postTopic);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", dateUpdated=");
		builder.append(dateUpdated);
		builder.append("]");
		return builder.toString();
	}

}
