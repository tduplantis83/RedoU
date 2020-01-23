package com.redou.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redou.entities.Avatar;
import com.redou.repositories.AvatarRepo;

@Service
public class AvatarServiceImpl implements AvatarService {

	@Autowired
	private AvatarRepo repo;

	@Override
	public Avatar getAvatarById(int id) {
		return repo.findById(id);
	}
	
	@Override
	public List<Avatar> getAllAvatar() {
		return repo.findAll();
	}
	
	@Override
	public List<Avatar> getAvatarBySex(String sex) {
		return repo.findBySex(sex);
	}

	@Override
	public List<Avatar> getAvatarByBodyType(String bodyType) {
		return repo.findByBodyType(bodyType);
	}

	@Override
	public Avatar createAvatar(Avatar avatar) {
		avatar.setDateCreated(LocalDate.now());
		avatar.setDateUpdated(LocalDate.now());

		return repo.saveAndFlush(avatar);
	}

	@Override
	public Avatar updateAvatar(Avatar avatar, int id) {
		Avatar toUpdate = repo.findById(id);

		toUpdate.setAvatarGroup(avatar.getAvatarGroup());
		toUpdate.setSex(avatar.getSex());
		toUpdate.setBodyType(avatar.getBodyType());
		toUpdate.setAvatarUrl(avatar.getAvatarUrl());
		toUpdate.setDateUpdated(LocalDate.now());

		return repo.saveAndFlush(toUpdate);
	}

	@Override
	public boolean deleteAvatar(int id) {

		try {
			Avatar toDelete = repo.findById(id);

			List<Avatar> groupToDelete = repo.findByAvatarGroup(toDelete.getAvatarGroup());

			for (Avatar avatar : groupToDelete) {
				repo.delete(avatar);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	

}
