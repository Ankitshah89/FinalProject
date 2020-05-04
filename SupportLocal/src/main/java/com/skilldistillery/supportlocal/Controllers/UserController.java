package com.skilldistillery.supportlocal.Controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.supportlocal.entities.User;
import com.skilldistillery.supportlocal.services.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4220" })
public class UserController {
	
	@Autowired
	private UserService userSvc;
	

	@GetMapping("users")
	public List<User> findAll(HttpServletRequest req, HttpServletResponse resp, Principal principal) {

		System.out.println(principal.getName());
		List<User> users = userSvc.findByEmail(principal.getName());
		

		if (users == null) {
			resp.setStatus(404);
		}
		if ( users != null) {
			if(users.size() == 0) {
				resp.setStatus(204);
				
			}
		}

		return users;
	}
	
	@GetMapping("users/{email}")
	public User getExistingUserByUsername(@PathVariable String email, HttpServletRequest req, Principal principal,
			HttpServletResponse resp) {
		User user = null;

		try {
			user = userSvc.findUserByEmail(email);
			if (user == null) {
				resp.setStatus(404);
				return null;
			}
			resp.setStatus(202);
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}

		return user;
		
	}
	
	@GetMapping("users/profile/{uid}")
	public User getUserById(HttpServletRequest req, HttpServletResponse resp, Principal principal, @PathVariable int uid) {
		User user = userSvc.findById(uid);
		if (user == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
			return user;
		}
		return null;
	}

	@PutMapping("users")
	public User replaceExistingUser(@RequestBody User user, HttpServletRequest req, Principal principal,
			HttpServletResponse resp) {
		
		
		try {
			user = userSvc.updateUser(user, principal.getName());
			if (user == null) {
				resp.setStatus(404);
				return null;
			}
			resp.setStatus(202);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(user.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
			return null;
		}
		
		return user;
	}
	
	@DeleteMapping("users/{userId}")
	public boolean deactivateUser(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId, Principal principal) {
		boolean isEnabled = userSvc.deactivateAndActivateUser(userId, principal.getName());
		if (isEnabled) {
			res.setStatus(200);
		}else {
			res.setStatus(404);
		}
		return isEnabled;
	}
	
	
}
