package com.skilldistillery.supportlocal.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Preference {
		//Preference
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="preference_type")
	private String preferenceType;
	
	@Column(name="preference_category")
	@Enumerated(EnumType.STRING)
	private PreferenceCategory preferenceCategory;
	
	@ManyToMany
	@JoinTable(name="business_preference", joinColumns = @JoinColumn(name = "preference_id")
	, inverseJoinColumns = @JoinColumn(name= "business_id"))
	@JsonIgnore
	private List<Business> buinesses;
	@ManyToMany
	@JoinTable(name="user_preference", joinColumns = @JoinColumn(name = "preference_id")
	, inverseJoinColumns = @JoinColumn(name= "user_id"))
	@JsonIgnore
	private List<User> users;
	//Methods begin

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPreferenceType() {
		return preferenceType;
	}

	public void setPreferenceType(String preferenceType) {
		this.preferenceType = preferenceType;
	}

	public PreferenceCategory getPreferenceCategory() {
		return preferenceCategory;
	}

	public void setPreferenceCategory(PreferenceCategory preferenceCategory) {
		this.preferenceCategory = preferenceCategory;
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
		Preference other = (Preference) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Preference [id=");
		builder.append(id);
		builder.append(", preferenceType=");
		builder.append(preferenceType);
		builder.append(", preferenceCategory=");
		builder.append(preferenceCategory);
		builder.append("]");
		return builder.toString();
	}

	public List<Business> getBuinesses() {
		return buinesses;
	}

	public void setBuinesses(List<Business> buinesses) {
		this.buinesses = buinesses;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	
	

}
