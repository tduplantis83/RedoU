package com.redou.services;

import java.util.List;

import com.redou.entities.Avatar;

public interface AvatarService {
	public Avatar getAvatarById(int id);
	
	public List<Avatar> getAllAvatar();
	
	public List<Avatar> getAvatarBySex(String sex);
	
	public List<Avatar> getAvatarByBodyType(String bodyType);
		
	public Avatar createAvatar(Avatar avatar);
	
	public Avatar updateAvatar(Avatar avatar, int id);
	
	public boolean deleteAvatar(int id);
	
}
