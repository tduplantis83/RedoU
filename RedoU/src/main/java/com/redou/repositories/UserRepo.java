package com.redou.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redou.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	public User findById(int id);

	public User findByUsername(String username);

	public List<User> findByEmailLike(String email);

	public List<User> findByEnabled(boolean enabled);

	public List<User> findByRole(String role);

}
