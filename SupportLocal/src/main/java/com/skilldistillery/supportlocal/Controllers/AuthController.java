package com.skilldistillery.supportlocal.Controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.supportlocal.entities.User;
import com.skilldistillery.supportlocal.services.AuthService;
import com.skilldistillery.supportlocal.services.UserService;

@RestController
@CrossOrigin({ "*", "http://localhost:4220" })
public class AuthController {

	@Autowired
	private AuthService authSvc;
	
	@Autowired
	private UserService userSvc;

	@PostMapping("/register")
	public User registerUser(@RequestBody User user, HttpServletResponse response) {
		if (user == null) {
			response.setStatus(400);
			return null;
		}

		boolean uniqueUser = authSvc.isUnique(user);

		if (!uniqueUser) {
			response.setStatus(403);
			return null;
		}

		user = authSvc.register(user);

		if (user == null) {
			response.setStatus(400);
			return null;
		}

		return user;
	}

	
	
	@GetMapping("/authenticate")
	public User authenticate(Principal principal ) {
		return userSvc.findUserByEmail(principal.getName());
	}
}
