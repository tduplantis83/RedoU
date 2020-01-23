package com.redou.services;

import java.util.List;

import com.redou.entities.PostTopic;

public interface PostTopicService {
	public PostTopic getPostTopicById(int id);

	public PostTopic getPostTopicByTopicName(String topicName);
	
	public List<PostTopic> getAllPostTopics();
	
	public PostTopic createPostTopic(PostTopic topic);

	public PostTopic updatePostTopic(PostTopic topic, int id);
	
	public boolean deletePostTopic(int id);
}
