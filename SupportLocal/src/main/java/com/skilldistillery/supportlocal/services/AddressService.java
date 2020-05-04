package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.Address;

public interface AddressService {
//	Business findBusinessByAddressBusinessId(int id); //Might move this to the business Service
	List<Address> indexAddress();
	Address createAddress(String email,Address address);
	Address updateAddress(String email,Address address);
	boolean deleteAddress(String email,int id);
	Address findById(int id);
	List<Address> generalSearch(String keywrod);
	List<Address> businessCategory(String categoryStr);
	

	

}
