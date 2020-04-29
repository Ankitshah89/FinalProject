package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.Business;

public interface BusinessService {
	//Index - Done
	List<Business> businessIndex();
	//Id - Done
	Business findById(int id);
	//Update
	Business updateBusiness(Business business, int id);
	//Destroy
	boolean deleteBusiness(int id);
	//Create - Done
	Business createBusiness(Business business);
	//FindByName
	List<Business> findBusinessByName(String name);
	//FindByDescription
	List<Business> findBusinessByDescription(String description);
	//FindByZipCode
	List<Business> findBusinessByZipCode(String zip);

}
