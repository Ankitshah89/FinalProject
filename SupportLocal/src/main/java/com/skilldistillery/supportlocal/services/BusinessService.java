package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.Business;

public interface BusinessService {
	//Index
	List<Business> businessIndex();
	//Id
	Business findById(int id);
	//Update
	Business updateBusiness(Business business, int id);
	//Destroy
	boolean deleteBusiness(Business business);
	//Create
	Business createBusiness(Business business);
	//FindByName
	List<Business> findBusinessByName(String name);
	//FindByDescription
	List<Business> findBusinessByDescription(String description);
	//FindByZipCode
	List<Business> findBusinessByZipCode(String zip);

}
