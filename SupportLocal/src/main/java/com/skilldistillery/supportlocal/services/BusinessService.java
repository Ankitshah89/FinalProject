package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.Business;

public interface BusinessService {
	//Index - Done
	List<Business> businessIndex();
	//Id - Done
	Business findById(int id);
	//Update
	Business updateBusiness(String email,Business business, int id);
	//Destroy
	boolean deleteBusiness(String email,int id);
	//Create - Done
	Business createBusiness(String email,Business business);
	//FindByName
	List<Business> findBusinessByName(String name);
	//FindByDescription
	List<Business> findBusinessByDescription(String description);
	//FindByZipCode
	List<Business> findBusinessByZipCode(String zip);

}
