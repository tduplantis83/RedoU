package com.redou.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redou.entities.Avatar;

public interface AvatarRepo extends JpaRepository<Avatar, Integer> {
	public Avatar findById(int id);

	public List<Avatar> findByAvatarGroup(int id);

	public List<Avatar> findBySex(String sex);
	
	public List<Avatar> findByBodyType(String bodyType);
}
