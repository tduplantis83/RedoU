package com.redou.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redou.entities.User;
import com.redou.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private AuthService authSvc;

	@Override
	public User getUserById(int id) {
		return repo.findById(id);
	}

	@Override
	public User getUserByUsernameExact(String username) {
		return repo.findByUsernameIgnoreCase(username);
	}

	@Override
	public List<User> getUserByUsernameContaining(String username) {
		return repo.findByUsernameIgnoreCaseContaining(username);
	}

	@Override
	public List<User> getUserByEmailContaining(String email) {
		return repo.findByEmailIgnoreCaseContaining(email);
	}

	@Override
	public List<User> getUserByEnabled(boolean enabled) {
		return repo.findByEnabled(enabled);
	}

	@Override
	public List<User> getUserByRole(String role) {
		return repo.findByRoleIgnoreCase(role);
	}
	
	@Override
	public List<User> getAllUsers() {
		return repo.findAll();
	}

	@Override
	public User createUser(User user) {
		user.setDateCreated(LocalDate.now());
		user.setDateUpdated(LocalDate.now());

		return repo.saveAndFlush(user);
	}

	@Override
	public User updateUser(User user, int id) {
		
		User toUpdate = repo.findById(id);

		if(user.getUsername() != null) {
			toUpdate.setUsername(user.getUsername());
		}
		if(user.getPassword() != null) {
			toUpdate.setPassword(authSvc.updateUserPassword(user, id));
		}
		if(user.getFirstName() != null) {
			toUpdate.setFirstName(user.getFirstName());
		}
		if(user.getLastName() != null) {
			toUpdate.setLastName(user.getLastName());
		}
		if(user.getBirthday() != null) {
			toUpdate.setBirthday(user.getBirthday());
		}
		if(user.getSex() != null) {
			toUpdate.setSex(user.getSex());
		}
		if(user.getUsername() != null) {
			toUpdate.setEmail(user.getEmail());
		}

		toUpdate.setEnabled(user.isEnabled());
		
		if(user.getRole() != null) {
			toUpdate.setRole(user.getRole());
		}

		toUpdate.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(toUpdate);
	}

	@Override
	public boolean deleteUser(int id) {
		try {
			User toDelete = repo.findById(id);

			repo.delete(toDelete);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
