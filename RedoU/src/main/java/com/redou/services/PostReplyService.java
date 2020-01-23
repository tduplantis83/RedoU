package com.redou.services;

import java.util.List;

import com.redou.entities.PostReply;

public interface PostReplyService {
	public PostReply getPostReplyById(int id);

	public List<PostReply> getPostReplyByReplyUserId(int replyUserId);

	public List<PostReply> getPostReplyByReplyUsername(String username);

	public List<PostReply> getPostReplyByOriginalPostId(int originalPostId);
	
	public List<PostReply> getPostReplyByOriginalPostUserId(int originalPostUserId);
	
	public List<PostReply> getPostReplyByOriginalPostUsername(String username);
	
	public PostReply createPostReply(PostReply reply);

	public PostReply createPostReplyToReply(PostReply reply, int id);

	public PostReply updatePostReply(PostReply reply, int id);
	
	public boolean deletePostReply(int id);
}
