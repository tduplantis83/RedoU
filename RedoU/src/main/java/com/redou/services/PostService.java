package com.redou.services;

import java.util.List;

import com.redou.entities.Post;

public interface PostService {
	public Post getPostById(int id);

	public List<Post> getPostByUserId(int userId);

	public List<Post> getPostByUsername(String username);

	public List<Post> getPostByPostTopic(String postTopic);

	public List<Post> getPostByTitleLike(String title);

	public List<Post> getPostByContentLike(String content);
	
	public Post createPost(Post post);

	public Post updatePost(Post post, int id);
	
	public boolean deletePost(int id);
}
