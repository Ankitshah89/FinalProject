package com.skilldistillery.supportlocal.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "article_comment")
public class ArticleComment {

	// F i e l d s

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "article_id")
	@JsonIgnore
	private Article article;

	private String content;

	@Column(name = "create_date")
	@CreationTimestamp
	private LocalDateTime createDate;

	@ManyToOne
	@JoinColumn(name = "inreply_to_id")
	@JsonIgnore
	private ArticleComment parentComment;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;

	// M e t h o d s

	public ArticleComment() {
		super();
	}

	public ArticleComment(int id, Article article, String content, LocalDateTime createDate,
			ArticleComment parentComment, User user) {
		super();
		this.id = id;
		this.article = article;
		this.content = content;
		this.createDate = createDate;
		this.parentComment = parentComment;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public ArticleComment getInReplyTo() {
		return parentComment;
	}

	public void setInReplyTo(ArticleComment parentComment) {
		this.parentComment = parentComment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArticleComment getParentComment() {
		return parentComment;
	}

	public void setParentComment(ArticleComment parentComment) {
		this.parentComment = parentComment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ArticleComment other = (ArticleComment) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ArticleComment [id=" + id + ", content=" + content + ", createDate=" + createDate + "]";
	}

}