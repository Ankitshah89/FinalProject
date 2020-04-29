package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.User;

public interface UserService {
	
	List<User> findAll();
	
	List<User> findByEmail(String email);

	User updateUser(User user, String email);
	
	User updateUserProfile( String email, User user);

	User findUserByUsername(String email);
	
	User create (User user);

}
