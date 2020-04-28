package com.skilldistillery.supportlocal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.supportlocal.entities.User;
import com.skilldistillery.supportlocal.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
   @Autowired
	private UserRepository userRepo;

	@Override
	public List<User> findAll(String username) {
		User user = userRepo.findUserByUsername(username);
		if (user.getRole().equals("Admin")) {
			return userRepo.findAll();
		}

		return null;
	}

	@Override
	public User updateUser(User user, String username) {

		User userAdmin = userRepo.findUserByUsername(username);

		Optional<User> userOpt = userRepo.findById(user.getId());

		if (userAdmin.getRole().equals("Admin")) {
			if (userOpt.isPresent()) {
				user.setActive(user.isActive());

				userRepo.saveAndFlush(user);
				return user;
			}
		}

		return null;
	}

	@Override
	public User updateUserProfile(String username, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByUsername(String username) {

		return userRepo.findUserByUsername(username);
	}

}
