package com.redou.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redou.entities.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {
	public Post findById(int id);

	public List<Post> findByUser_Id(int userId);

	public List<Post> findByUser_Username(String username);

	public List<Post> findByPostTopicLike(String postTopic);

	public List<Post> findByTitleLike(String title);

	public List<Post> findByContentLike(String content);

}
