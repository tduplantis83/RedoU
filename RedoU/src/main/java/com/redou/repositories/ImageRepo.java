package com.redou.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redou.entities.Image;

public interface ImageRepo extends JpaRepository<Image, Integer> {
	public Image findById(int id);

	public List<Image> findByUser_Id(int userId);

	public List<Image> findByUser_UsernameIgnoreCase(String username);

}
