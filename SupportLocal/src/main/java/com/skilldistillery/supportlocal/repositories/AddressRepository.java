package com.skilldistillery.supportlocal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.supportlocal.entities.Address;
import com.skilldistillery.supportlocal.entities.Business;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	Business findBusinessByBusinessId(int id);

}
