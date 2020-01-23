package com.redou.services;

import java.util.List;

import com.redou.entities.UserAvatar;

public interface UserAvatarService {
	public UserAvatar getById(int id);
	
	public List<UserAvatar> getByUserId(int userId);

	public List<UserAvatar> getByUsername(String username);
	
	//create 5 records for user with the same Avatar.avatarGroup
	public List<UserAvatar> createUserAvatar(int userId, int avatarGroup);
	
	//update 5 records for user with the same Avatar.avatarGroup
	public List<UserAvatar> updateUserAvatar(int userId, int avatarGroup);
	
	//update 5 records for user, setting one with given bodyType to current = true & all others to false
	public UserAvatar updateUserCurrentAvatar(int userId, String bodyType);
	
	public boolean deleteUserAvatar(int userId);
	
}
