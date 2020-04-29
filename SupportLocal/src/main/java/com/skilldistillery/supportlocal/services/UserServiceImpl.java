package com.skilldistillery.supportlocal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.supportlocal.entities.Role;
import com.skilldistillery.supportlocal.entities.User;
import com.skilldistillery.supportlocal.repositories.AddressRepository;
import com.skilldistillery.supportlocal.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<User> findByEmail(String email) {
		User user = userRepo.findUserByEmail(email);
		System.out.println(user.getRole());
		
		if (user.getRole().equals(Role.Admin)) {
			return userRepo.findAll();
		}

		return null;
	}

	@Override
	public User updateUser(User user, String email) {

		User userAdmin = userRepo.findUserByEmail(email);

		Optional<User> userOpt = userRepo.findById(user.getId());

		if (userAdmin.getRole().equals(Role.Admin)) {
			if (userOpt.isPresent()) {
				user.setActive(user.isActive());

				userRepo.saveAndFlush(user);
				return user;
			}
		}
		return null;
	}


	@Override
	public User findUserByEmail(String email) {

		return userRepo.findUserByEmail(email);
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public User create(User user) {
		return userRepo.saveAndFlush(user);
	}

}
