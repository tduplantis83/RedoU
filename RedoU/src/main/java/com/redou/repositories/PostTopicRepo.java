package com.redou.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.redou.entities.PostTopic;

public interface PostTopicRepo extends JpaRepository<PostTopic, Integer> {
	public PostTopic findById(int id);

	public PostTopic findByTopicNameIgnoreCase(String topicName);

}
