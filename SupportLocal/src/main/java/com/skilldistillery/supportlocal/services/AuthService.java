package com.skilldistillery.supportlocal.services;

import com.skilldistillery.supportlocal.entities.User;

public interface AuthService {
	
	User register(User user);

	boolean isUnique(User user);

}
