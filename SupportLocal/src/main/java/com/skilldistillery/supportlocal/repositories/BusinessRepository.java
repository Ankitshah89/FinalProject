package com.skilldistillery.supportlocal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.supportlocal.entities.Business;
import com.skilldistillery.supportlocal.entities.User;

public interface BusinessRepository extends JpaRepository<Business, Integer> {
	Business findByManager(User user);
	List<Business> findByName(String name);
	List<Business> findByDescription(String description);
	List<Business> findByAddressPostalCode(String zip);
}
