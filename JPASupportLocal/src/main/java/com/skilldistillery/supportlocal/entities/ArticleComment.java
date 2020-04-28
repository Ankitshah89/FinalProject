package com.skilldistillery.supportlocal.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="article_comment")
public class ArticleComment {
	
	// F i e l d s 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="article_id")
	private int articleId;
	
	private String content;
	
	@Column(name="create_date")
	private LocalDateTime createDate;
	
	@Column(name="inreply_to_id")
	private int inReplyToId;
	
	@Column(name="user_id")
	private int userId;
	
	// M e t h o d s

	public ArticleComment() {
		super();
	}

	public ArticleComment(int id, int articleId, String content, LocalDateTime createDate, int inReplyToId,
			int userId) {
		super();
		this.id = id;
		this.articleId = articleId;
		this.content = content;
		this.createDate = createDate;
		this.inReplyToId = inReplyToId;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
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

	public int getInReplyToId() {
		return inReplyToId;
	}

	public void setInReplyToId(int inReplyToId) {
		this.inReplyToId = inReplyToId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
		return "ArticleComment [id=" + id + ", articleId=" + articleId + ", content=" + content + ", createDate="
				+ createDate + ", inReplyToId=" + inReplyToId + ", userId=" + userId + "]";
	}
	
	
	
	

}
