package com.skilldistillery.supportlocal.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Article {

	// F i e l d s

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonIgnoreProperties(value = {"businesses", "reviews", "articles", "reviewComments"})
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String title;

	private String content;
	private boolean active;

	@ManyToOne
	@JsonIgnoreProperties(value = "manager")
	@JoinColumn(name="business_id")
	private Business business;

	@Column(name = "create_at")
	@CreationTimestamp
	private LocalDateTime createAt;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToMany(mappedBy = "article")
	@JsonIgnoreProperties(value = "articles")
	private List<ArticleComment> articleComments;

	// M e t h o d s

	public Article() {
		super();
	}

	public List<ArticleComment> getArticleComments() {
		return articleComments;
	}

	public void setArticleComments(List<ArticleComment> articleComments) {
		this.articleComments = articleComments;
	}

	public Article(int id, User user, String title, String content, LocalDateTime createAt, String imageUrl) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
		this.createAt = createAt;
		this.imageUrl = imageUrl;
	}

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

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
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
		Article other = (Article) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Article(int id, User user, String title, String content, boolean active, Business business,
			LocalDateTime createAt, String imageUrl, List<ArticleComment> articleComments) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
		this.active = active;
		this.business = business;
		this.createAt = createAt;
		this.imageUrl = imageUrl;
		this.articleComments = articleComments;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + ", createAt=" + active + ", active="
				+ createAt + ", imageUrl=" + imageUrl + "]";
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}