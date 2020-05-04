package com.skilldistillery.supportlocal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.supportlocal.entities.Role;
import com.skilldistillery.supportlocal.entities.User;
import com.skilldistillery.supportlocal.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	BusinessService busServ;

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
	public User findById(int uid) {
		Optional<User> optUser = userRepo.findById(uid);
		if (optUser.isPresent()) {
			User user = optUser.get();
			if (user != null) {
				return user;
			}
		}
		return null;

	}

	@Override
	public User updateUser(User user, String email) {
		String encodedPW = encoder.encode(user.getPassword());

		Optional<User> userOpt = userRepo.findByEmail(email);

		if (userOpt.isPresent()) {
			User managedUser = userOpt.get();
			managedUser.setFirstName(user.getFirstName());
			managedUser.setLastName(user.getLastName());
			managedUser.setPhone(user.getPhone());
			managedUser.setEmail(user.getEmail());
			managedUser.setPassword(encodedPW);
			userRepo.saveAndFlush(managedUser);
			return managedUser;
		}
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		User manUser = userRepo.findUserByEmail(email);
		manUser.setBusinesses(busServ.findByManager(manUser));
		System.out.println(manUser.getBusinesses());
		return manUser;
	}

	@Override
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@Override
	public User create(User user) {
		return userRepo.saveAndFlush(user);
	}

	@Override
	public Boolean deactivateAndActivateUser(int userId, String email) {
		User user = userRepo.findUserByEmail(email);
		Optional<User> optUser = userRepo.findById(userId);
		if (optUser.isPresent() && user != null) {
			User updateUser = optUser.get();
			updateUser.setActive(!updateUser.isActive());
			userRepo.save(updateUser);
			return true;
		} else {
			return false;
		}
	}

}
