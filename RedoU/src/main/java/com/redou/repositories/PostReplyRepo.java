package com.redou.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redou.entities.PostReply;

public interface PostReplyRepo extends JpaRepository<PostReply, Integer> {
	public PostReply findById(int id);

	public List<PostReply> findByReplyUser_Id(int replyUserId);

	public List<PostReply> findByReplyUser_Username(String username);

	public PostReply findByOriginalPost_Id(int originalPostId);

}
