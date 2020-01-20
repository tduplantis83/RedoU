package com.redou.services;

import java.util.List;

import com.redou.entities.Image;

public interface ImageService {
	public Image getImageById(int id);

	public List<Image> getImageByUserId(int userId);

	public List<Image> getImageByUsername(String username);
}
