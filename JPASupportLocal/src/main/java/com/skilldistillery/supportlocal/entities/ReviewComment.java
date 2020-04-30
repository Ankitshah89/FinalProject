package com.skilldistillery.supportlocal.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="review_comment")
public class ReviewComment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "review_id")
	@JsonIgnore
	private Review review;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	private String content;

	@Column(name = "create_date")
	private LocalDateTime createdAt;
	
	@Column(name = "date_updated")
	private LocalDateTime updatedAt;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "inreply_to_id")
	private ReviewComment parentComment;
	
	private boolean active;


	public int getId() {
		return id;
	}


	public ReviewComment(int id, Review review, User user, String content, LocalDateTime createdAt,
			LocalDateTime updatedAt, ReviewComment parentComment, Boolean active) {
		super();
		this.id = id;
		this.review = review;
		this.user = user;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.parentComment = parentComment;
		this.active = active;
	}


	public ReviewComment() {
		super();
	}


	public ReviewComment(int id, Review review, User user, String content, LocalDateTime createdAt,
			LocalDateTime updatedAt, ReviewComment parentComment) {
		super();
		this.id = id;
		this.review = review;
		this.user = user;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.parentComment = parentComment;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Review getReview() {
		return review;
	}


	public void setReview(Review review) {
		this.review = review;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}


	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	public ReviewComment getParentComment() {
		return parentComment;
	}


	public void setParentComment(ReviewComment parentComment) {
		this.parentComment = parentComment;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + id;
		result = prime * result + ((parentComment == null) ? 0 : parentComment.hashCode());
		result = prime * result + ((review == null) ? 0 : review.hashCode());
		result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
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
		ReviewComment other = (ReviewComment) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (id != other.id)
			return false;
		if (parentComment == null) {
			if (other.parentComment != null)
				return false;
		} else if (!parentComment.equals(other.parentComment))
			return false;
		if (review == null) {
			if (other.review != null)
				return false;
		} else if (!review.equals(other.review))
			return false;
		if (updatedAt == null) {
			if (other.updatedAt != null)
				return false;
		} else if (!updatedAt.equals(other.updatedAt))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "ReviewComment [id=" + id + ", content=" + content + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", active=" + active + "]";
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	

}
