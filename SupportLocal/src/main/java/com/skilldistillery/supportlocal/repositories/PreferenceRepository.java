package com.skilldistillery.supportlocal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.supportlocal.entities.Business;
import com.skilldistillery.supportlocal.entities.Preference;
import com.skilldistillery.supportlocal.entities.PreferenceCategory;

public interface PreferenceRepository extends JpaRepository<Preference, Integer> {
	
	List<Preference> findByPreferenceTypeLike(String type);
	
//	List<Preference> findByPreferenceCategoryLike(PreferenceCategory category);
	
//	@Query("SELECT p FROM Preference p WHERE p.preferenceCategory = :category")
	List<Preference> findByPreferenceCategory(PreferenceCategory category);
	
//	List<Business> findBy_PreferenceCategory(PreferenceCategory category);

}
