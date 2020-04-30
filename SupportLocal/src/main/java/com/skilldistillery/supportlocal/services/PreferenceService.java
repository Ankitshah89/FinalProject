package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.Preference;

public interface PreferenceService {
	List<Preference> prefIndex();
	//Create
	Preference createPref(String email,Preference pref);
	//Update
	Preference updatePref(String email,Preference pref, int id);
	//Destroy
	boolean deleteById(String email,int id);
	//Retrieve
	Preference prefById(int id);
	//SearchByType
	List<Preference> searchByType(String type);
	//SearchByCategory
	List<Preference> searchByCategory(String category);

}
