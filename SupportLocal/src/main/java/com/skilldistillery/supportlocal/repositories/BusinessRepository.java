package com.skilldistillery.supportlocal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.supportlocal.entities.Business;
import com.skilldistillery.supportlocal.entities.PreferenceCategory;

public interface BusinessRepository extends JpaRepository<Business, Integer> {

	List<Business> findByNameLike(String name);

	List<Business> findByDescriptionLike(String description);

	List<Business> findByAddressPostalCodeLike(String zip);
	
	List<Business> findByManagerId(Integer id);
	
	List<Business> findByPreferencesPreferenceCategory(PreferenceCategory category);
	
	List<Business> findByActiveIsTrue();

}
