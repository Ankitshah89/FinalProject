package com.skilldistillery.supportlocal.services;

import java.util.List;

import com.skilldistillery.supportlocal.entities.User;

public interface UserService {
	
	List<User> findAll(String username);

	User updateUser(User user, String username);
	
	User updateUserProfile( String username, User user);

	User findUserByUsername(String username);

}
