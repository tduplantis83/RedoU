package com.redou.services;

import java.util.List;

import com.redou.entities.User;

public interface UserService {
	public User getUserById(int id);
	
	public User getUserByUsernameExact(String username);

	public List<User> getUserByUsername(String username);

	public List<User> getUserByEmail(String email);

	public List<User> getUserByEnabled(boolean enabled);

	public List<User> getUserByRole(String role);
	
	public User createUser(User user);

	public User updateUser(User user);
	
	public boolean deleteUser(int id);
}
