package com.redou.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "post_reply")
public class PostReply {

	// FIELDS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnoreProperties({ "userCurrentGoals", "userDailyCaloricIntakes", "userDailyExerciseCaloricDeficits",
			"userImages", "userAvatars", "userBodyMeasurementMetrics", "userPosts", "userPostReplies" })
	@ManyToOne
	@JoinColumn(name = "reply_user_id")
	private User replyUser;

	@JsonIgnoreProperties({ "originalPostReplies" })
	@ManyToOne
	@JoinColumn(name = "originalpost_id")
	private Post originalPost;

	@JsonIgnore
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name = "reply_to_reply_id")
	private PostReply replyToReply;

	@JsonIgnoreProperties({"replyToReply", "originalPost"})
	@OneToMany(mappedBy = "replyToReply")
	private List<PostReply> repliesToPostReply;

	@Column(name = "replycontent")
	private String replyContent;

	@Column(name = "datecreated")
	private LocalDate dateCreated;

	@Column(name = "dateupdated")
	private LocalDate dateUpdated;

	// CONSTRUCTOR
	public PostReply() {

	}

	// GETS & SETS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(User replyUser) {
		this.replyUser = replyUser;
	}

	public Post getOriginalPost() {
		return originalPost;
	}

	public void setOriginalPost(Post originalPost) {
		this.originalPost = originalPost;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
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

	public PostReply getReplyToReply() {
		return replyToReply;
	}

	public void setReplyToReply(PostReply replyToReply) {
		this.replyToReply = replyToReply;
	}

	public List<PostReply> getRepliesToPostReply() {
		return repliesToPostReply;
	}

	public void setRepliesToPostReply(List<PostReply> repliesToPostReply) {
		this.repliesToPostReply = repliesToPostReply;
	}

	// HASH & EQUALS
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((dateUpdated == null) ? 0 : dateUpdated.hashCode());
		result = prime * result + id;
		result = prime * result + ((originalPost == null) ? 0 : originalPost.hashCode());
		result = prime * result + ((repliesToPostReply == null) ? 0 : repliesToPostReply.hashCode());
		result = prime * result + ((replyContent == null) ? 0 : replyContent.hashCode());
		result = prime * result + ((replyToReply == null) ? 0 : replyToReply.hashCode());
		result = prime * result + ((replyUser == null) ? 0 : replyUser.hashCode());
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
		PostReply other = (PostReply) obj;
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
		if (originalPost == null) {
			if (other.originalPost != null)
				return false;
		} else if (!originalPost.equals(other.originalPost))
			return false;
		if (repliesToPostReply == null) {
			if (other.repliesToPostReply != null)
				return false;
		} else if (!repliesToPostReply.equals(other.repliesToPostReply))
			return false;
		if (replyContent == null) {
			if (other.replyContent != null)
				return false;
		} else if (!replyContent.equals(other.replyContent))
			return false;
		if (replyToReply == null) {
			if (other.replyToReply != null)
				return false;
		} else if (!replyToReply.equals(other.replyToReply))
			return false;
		if (replyUser == null) {
			if (other.replyUser != null)
				return false;
		} else if (!replyUser.equals(other.replyUser))
			return false;
		return true;
	}

	// TO STRING
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PostReply [id=");
		builder.append(id);
		builder.append(", replyUser=");
		builder.append(replyUser);
		builder.append(", originalPost=");
		builder.append(originalPost);
		builder.append(", replyToReply=");
		builder.append(replyToReply);
		builder.append(", replyContent=");
		builder.append(replyContent);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", dateUpdated=");
		builder.append(dateUpdated);
		builder.append("]");
		return builder.toString();
	}

}
