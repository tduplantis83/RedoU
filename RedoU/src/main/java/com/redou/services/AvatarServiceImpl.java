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
	public Avatar createAvatar(Avatar avatar) {
		avatar.setDateCreated(LocalDate.now());
		avatar.setDateUpdated(LocalDate.now());

		return repo.saveAndFlush(avatar);
	}

	@Override
	public Avatar updateAvatar(Avatar avatar) {
		Avatar toUpdate = repo.findById(avatar.getId());

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
