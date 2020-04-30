package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.Preference;

public interface PreferenceService {
	List<Preference> prefIndex();
	//Create
	Preference createPref(Preference pref);
	//Update
	Preference updatePref(Preference pref, int id);
	//Destroy
	boolean deleteById(int id);
	//Retrieve
	Preference prefById(int id);
	//SearchByType
	List<Preference> searchByType(String type);
	//SearchByCategory
	List<Preference> searchByCategory(String category);

}
