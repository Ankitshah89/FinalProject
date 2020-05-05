package com.skilldistillery.supportlocal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.supportlocal.entities.Address;
import com.skilldistillery.supportlocal.entities.Business;
import com.skilldistillery.supportlocal.entities.PreferenceCategory;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	Business findBusinessByBusinessId(int id);
	List<Address> findByBusinessDescriptionLikeOrPostalCodeLikeOrBusinessNameLikeAndBusinessActiveIsTrue(String des, String post, String name);
	
	List<Address> findByBusinessPreferencesPreferenceCategoryAndBusinessActiveIsTrue(PreferenceCategory category);

	Address findByBusinessIdAndBusinessActiveIsTrue(int id);
}
