package com.redou.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redou.entities.Post;

public interface PostRepo extends JpaRepository<Post, Integer> {
	public Post findById(int id);

	public List<Post> findByUser_Id(int userId);

	public List<Post> findByUser_UsernameIgnoreCase(String username);

	public List<Post> findByPostTopic_TopicNameIgnoreCase(String postTopic);

	public List<Post> findByTitleIgnoreCaseContaining(String title);

	public List<Post> findByContentIgnoreCaseContaining(String content);

}
