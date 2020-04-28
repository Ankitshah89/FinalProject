package com.skilldistillery.supportlocal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.supportlocal.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findUserByEmail(String email);

}
