package com.skilldistillery.supportlocal.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.supportlocal.entities.Address;
import com.skilldistillery.supportlocal.entities.Business;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	Business findBusinessByBusinessId(int id);
	List<Address> findByBusinessDescriptionLikeOrPostalCodeLikeOrBusinessNameLike(String des, String post, String name);

}
