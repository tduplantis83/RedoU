package com.redou.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redou.entities.Avatar;

public interface AvatarRepo extends JpaRepository<Avatar, Integer> {
	public Avatar findById(int id);

	public List<Avatar> findByUser_Id(int userId);

	public List<Avatar> findByUser_UsernameIgnoreCase(String username);

	public Avatar findByUser_IdAndBodyTypeIgnoreCase(int userId, String bodyType);

}
