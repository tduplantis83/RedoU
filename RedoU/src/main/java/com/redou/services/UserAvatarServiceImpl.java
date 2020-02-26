package com.redou.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redou.entities.Avatar;
import com.redou.entities.User;
import com.redou.entities.UserAvatar;
import com.redou.repositories.AvatarRepo;
import com.redou.repositories.UserAvatarRepo;
import com.redou.repositories.UserRepo;

@Service
public class UserAvatarServiceImpl implements UserAvatarService{
	
	@Autowired
	private UserAvatarRepo repo;
	
	@Autowired
	private AvatarRepo avRepo;
	
	@Autowired
	private UserRepo uRepo;

	@Override
	public UserAvatar getById(int id) {
		return repo.findById(id);
	}

	@Override
	public List<UserAvatar> getByUserId(int userId) {
		return repo.findByUser_Id(userId);
	}

	@Override
	public List<UserAvatar> getByUsername(String username) {
		return repo.findByUser_UsernameIgnoreCase(username);
	}

	@Override
	public List<UserAvatar> createUserAvatar(int userId, int avatarGroup) {
		List<Avatar> avatars = avRepo.findByAvatarGroup(avatarGroup);
		List<UserAvatar> userAvatars = new ArrayList<>();
		User user = uRepo.findById(userId);
		
		for (Avatar avatar : avatars) {
			UserAvatar ua = new UserAvatar();
			ua.setUser(user);
			ua.setAvatar(avatar);
			ua.setDateCreated(LocalDate.now());
			ua.setDateUpdated(LocalDate.now());
			
			repo.saveAndFlush(ua);
			
			userAvatars.add(ua);
		}
		

		return userAvatars;
	}

	@Override
	public List<UserAvatar>  updateUserAvatar(int userId, int avatarGroup) {
		//avatars the user has now
		List<UserAvatar> currentAvatars = repo.findByUser_Id(userId);
		//avatars the user wants to change to
		List<Avatar> avatars = avRepo.findByAvatarGroup(avatarGroup);
		
		for(int i = 0; i < currentAvatars.size(); i++) {
			currentAvatars.get(i).setAvatar(avatars.get(i));
			currentAvatars.get(i).setDateUpdated(LocalDate.now());
			repo.saveAndFlush(currentAvatars.get(i));
		}
		
		return currentAvatars;
		
	}

	@Override
	public UserAvatar updateUserCurrentAvatar(int userId, String bodyType) {
		List<UserAvatar> currentAvatars = repo.findByUser_Id(userId);
		UserAvatar newCurrent = new UserAvatar();
		
		for(int i = 0; i < currentAvatars.size(); i++) {
			if(currentAvatars.get(i).getAvatar().getBodyType().equalsIgnoreCase(bodyType)) {
				currentAvatars.get(i).setCurrent(true);
				currentAvatars.get(i).setDateUpdated(LocalDate.now());
				newCurrent = currentAvatars.get(i);
				repo.saveAndFlush(currentAvatars.get(i));
			}
			else {
				currentAvatars.get(i).setCurrent(false);
				currentAvatars.get(i).setDateUpdated(LocalDate.now());
				repo.saveAndFlush(currentAvatars.get(i));
			}
		}
		
		return newCurrent;
		
	}

	@Override
	public boolean deleteUserAvatar(int userId) {
		try {
			List<UserAvatar> toDelete = repo.findByUser_Id(userId);

			for (UserAvatar userAvatar : toDelete) {
				repo.delete(userAvatar);
			}
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
