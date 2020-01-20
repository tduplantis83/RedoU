package com.redou.services;

import java.util.List;

import com.redou.entities.Avatar;

public interface AvatarService {
	public Avatar getAvatarById(int id);

	public List<Avatar> getAvatarByUserId(int userId);

	public List<Avatar> getAvatarByUsername(String username);

	public Avatar getAvatarByUserIdAndBodyType(int userId, String bodyType);
}
