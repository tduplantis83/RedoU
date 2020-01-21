package com.redou.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redou.entities.UserAvatar;

public interface UserAvatarRepo extends JpaRepository<UserAvatar, Integer>{
	public UserAvatar findById(int id);
	
	public List<UserAvatar> findByUser_Id(int userId);

}
