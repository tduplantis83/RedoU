package com.redou.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redou.entities.User;
import com.redou.repositories.UserRepo;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo repo;

	@Override
	public User getUserById(int id) {
		return repo.findById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		return repo.findByUsernameIgnoreCase(username);
	}

	@Override
	public List<User> getUserByEmail(String email) {
		return repo.findByEmailIgnoreCaseLike("%" + email + "%");
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
	public User createUser(User user) {
		user.setDateCreated(LocalDate.now());
		user.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(user);
	}

	@Override
	public User updateUser(User user) {
		User toUpdate = repo.findById(user.getId());
		
		toUpdate.setUsername(user.getUsername());
		toUpdate.setPassword(user.getPassword());
		toUpdate.setFirstName(user.getFirstName());
		toUpdate.setLastName(user.getLastName());
		toUpdate.setBirthday(user.getBirthday());
		toUpdate.setSex(user.getSex());
		toUpdate.setEmail(user.getEmail());
		toUpdate.setEnabled(user.isEnabled());
		toUpdate.setRole(user.getRole());
		toUpdate.setDateCreated(user.getDateCreated());
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
