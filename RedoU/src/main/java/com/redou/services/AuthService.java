package com.redou.services;

import com.redou.entities.User;

public interface AuthService {
	public User register(User user);

	public boolean isUserUsernameUnique(String username);

	public boolean isUserEmailUnique(String email);

	public boolean isUserUnique(String username, String email);
}
