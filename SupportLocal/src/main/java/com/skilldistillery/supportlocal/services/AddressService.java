package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.Address;

public interface AddressService {
//	Business findBusinessByAddressBusinessId(int id); //Might move this to the business Service
	List<Address> indexAddress();
	Address createAddress(Address address);
	Address updateAddress(Address address);
	boolean deleteAddress(int id);
	Address findById(int id);
	

	

}
