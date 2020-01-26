package com.redou.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.redou.entities.User;
import com.redou.services.AuthService;
import com.redou.services.UserService;

@RestController
@CrossOrigin({ "*", "http://localhost:4444" })
public class AuthController {
	
	@Autowired
	private AuthService authSvc;
	
	@Autowired
	private UserService userSvc;

	@PostMapping("register")
	public User register(@RequestBody User user, HttpServletResponse res) {

		if (!authSvc.isUserUnique(user.getUsername(), user.getEmail())) {
			user = null;
			res.setStatus(400);
			return null;
		}
		else {
			user = authSvc.register(user);
			res.setStatus(201);
			return user;
		}
	}

	@GetMapping("authenticate")
	public Principal authenticate(Principal principal, HttpServletResponse res) {
		
		User loggedInUser = userSvc.getUserByUsernameExact(principal.getName());
		if(!loggedInUser.isEnabled()) {
			principal = null;
			res.setStatus(401);
			return principal;
		}
		else {
			res.setStatus(200);
			return principal;
		}
	}


}
