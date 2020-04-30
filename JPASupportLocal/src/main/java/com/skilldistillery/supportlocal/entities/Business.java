package com.skilldistillery.supportlocal.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Business {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String description;
	
	private String phone;
	
	private boolean active;
	
	@OneToOne(mappedBy="business")
	@JsonBackReference(value="businessToAddress")
	private Address address;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@JoinColumn(name="manager_id")
	@ManyToOne
	@JsonBackReference(value = "businessToUserManager")
	private User manager;
	
	
	@OneToMany(mappedBy="business")
	@JsonManagedReference(value = "businessToArticle")
	private List<Article> articles;
	
	@OneToMany(mappedBy="business")
//	@JsonBackReference(value="BusinessToReview")
	@JsonIgnore
	private List<Review> reviews;
	
	@ManyToMany
	@JoinTable(name = "user_favourite_business",
				joinColumns = @JoinColumn(name="business_id"),
				inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JsonIgnore
	private List<User> users;
	
	@ManyToMany
	@JoinTable(name = "business_preference",
	joinColumns = @JoinColumn(name="business_id"),
	inverseJoinColumns = @JoinColumn(name = "preference_id"))
	@JsonIgnore
	private List<Preference> preferences;
	///Methods Begin

	public Business() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
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
		Business other = (Business) obj;
		if (active != other.active)
			return false;
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Business [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", active=");
		builder.append(active);
		builder.append(", imageUrl=");
		builder.append(imageUrl);
		builder.append(", manager=");
		builder.append(manager);
		builder.append("]");
		return builder.toString();
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Preference> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<Preference> preferences) {
		this.preferences = preferences;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
