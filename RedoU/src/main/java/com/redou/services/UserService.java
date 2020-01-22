package com.redou.services;

import java.util.List;

import com.redou.entities.User;

public interface UserService {
	public User getUserById(int id);
	
	public User getUserByUsernameExact(String username);
	
	public List<User> getUserByUsernameContaining(String username);

	public List<User> getUserByEmailContaining(String email);

	public List<User> getUserByEnabled(boolean enabled);

	public List<User> getUserByRole(String role);
	
	public User createUser(User user);

	public User updateUser(User user);
	
	public boolean deleteUser(int id);
}
