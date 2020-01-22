package com.redou.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.redou.entities.User;
import com.redou.repositories.UserRepo;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private UserService userSvc;

	@Autowired
	private PasswordEncoder encoder;

	
	@Override
	public User register(User user) {

		String encrypted = encoder.encode(user.getPassword());
		user.setPassword(encrypted);
		user.setEnabled(true);
		user.setRole("user");
		
		return userSvc.createUser(user);
	}

	@Override
	public boolean isUserUnique(String username, String email) {
		List<User> allUsers = uRepo.findAll();

		boolean isUnique = true;

		for (User user : allUsers) {
			if ((user.getEmail().equalsIgnoreCase(email)) || (user.getUsername().equalsIgnoreCase(username))) {
				isUnique = false;
			}

		}

		return isUnique;
	}

	@Override
	public boolean isUserUsernameUnique(String username) {
		List<User> allUsers = uRepo.findAll();
		
		boolean isUnique = true;
		
		for (User user : allUsers) {
			if ((user.getUsername().equalsIgnoreCase(username))) {
				isUnique = false;
			}
			
		}
		
		return isUnique;
	}

	@Override
	public boolean isUserEmailUnique(String email) {
		List<User> allUsers = uRepo.findAll();
		
		boolean isUnique = true;
		
		for (User user : allUsers) {
			if ((user.getEmail().equalsIgnoreCase(email))) {
				isUnique = false;
			}
			
		}
		
		return isUnique;
	}

	@Override
	public User updateUser(User user, int id) {
		System.out.println("****** IN AUTH SVC IMPL UPDATE USER ******");
		System.out.println("****** USER before update password: " + user + " ******");
		
		String encrypted = encoder.encode(user.getPassword());
		user.setPassword(encrypted);
		
		System.out.println("****** USER after update password: " + user + " ******");
		return userSvc.updateUser(user, id);
	}

}

