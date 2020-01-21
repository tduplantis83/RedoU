package com.redou.services;

import java.util.List;

import com.redou.entities.PostReply;

public interface PostReplyService {
	public PostReply getPostReplyById(int id);

	public List<PostReply> getPostReplyByReplyUserId(int replyUserId);

	public List<PostReply> getPostReplyByReplyUsername(String username);

	public PostReply getPostReplyByOriginalPostId(int originalPostId);
	
	public PostReply createPostReply(PostReply reply);

	public PostReply updatePostReply(PostReply reply);
	
	public boolean deletePostReply(int id);
}