package com.skilldistillery.supportlocal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.supportlocal.entities.Role;
import com.skilldistillery.supportlocal.entities.User;
import com.skilldistillery.supportlocal.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User register(User user) {
		String encodedPW = encoder.encode(user.getPassword());
		user.setPassword(encodedPW); // only persist encoded password
		user.setActive(true);
		user.setRole(Role.User);
		
		// set other fields to default values

		userRepo.saveAndFlush(user);
		return user;
	}

	@Override
	public boolean isUnique(User user) {
		User userFound = userRepo.findUserByEmail(user.getEmail());

		if (userFound != null) {
			return false;
		}
		
		return true;
	}

}
