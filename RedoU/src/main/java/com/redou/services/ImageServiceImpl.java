package com.redou.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redou.entities.Image;
import com.redou.repositories.ImageRepo;

@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private ImageRepo repo;

	@Override
	public Image getImageById(int id) {
		return repo.findById(id);
	}

	@Override
	public List<Image> getImageByUserId(int userId) {
		return repo.findByUser_Id(userId);
	}

	@Override
	public List<Image> getImageByUsername(String username) {
		return repo.findByUser_UsernameIgnoreCase(username);
	}

	@Override
	public Image createImage(Image img) {
		img.setDateCreated(LocalDate.now());
		img.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(img);
	}

	@Override
	public Image updateImage(Image img, int id) {
		Image toUpdate = repo.findById(id);
		
		toUpdate.setUser(img.getUser());
		toUpdate.setImageUrl(img.getImageUrl());
		toUpdate.setDateCreated(img.getDateCreated());
		toUpdate.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(toUpdate);
	}

	@Override
	public boolean deleteImage(int id) {
		try {
			Image toDelete = repo.findById(id);

			repo.delete(toDelete);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
