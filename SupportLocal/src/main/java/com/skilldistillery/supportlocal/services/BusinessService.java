package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.Business;
import com.skilldistillery.supportlocal.entities.User;

public interface BusinessService {
	//Index - Done
	List<Business> businessIndex();
	//Id - Done
	Business findById(int id);
	//Update
	Business updateBusiness(String email,Business business, int id);
	//Destroy

	//Create - Done
	Business createBusiness(String email,Business business);
	//FindByName
	List<Business> findBusinessByName(String name);
	//FindByDescription
	List<Business> findBusinessByDescription(String description);
	//FindByZipCode
	List<Business> findBusinessByZipCode(String zip);
	
	List<Business> findByManager(User user);
	List<Business> findByPreferenceCategory(String categoryStr);
	Boolean deactivateAndActivateBusiness(String email, int id);
	
	List<Business> adminIndexBusiness();
	
	
//	List<Business> findBusinessesByUsername(U)

}
