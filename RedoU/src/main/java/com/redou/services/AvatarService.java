package com.redou.services;

import com.redou.entities.Avatar;

public interface AvatarService {
	public Avatar getAvatarById(int id);
	
	public Avatar createAvatar(Avatar avatar);
	
	public Avatar updateAvatar(Avatar avatar);
	
	public boolean deleteAvatar(int id);
}
