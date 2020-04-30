package com.skilldistillery.supportlocal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.supportlocal.entities.Preference;

public interface PreferenceRepository extends JpaRepository<Preference, Integer> {
	
	List<Preference> findByPreferenceTypeLike(String type);
	
	List<Preference> findByPreferenceCategoryLike(String category);

}
