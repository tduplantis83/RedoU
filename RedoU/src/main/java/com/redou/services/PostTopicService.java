package com.redou.services;

import com.redou.entities.PostTopic;

public interface PostTopicService {
	public PostTopic getPostTopicById(int id);

	public PostTopic getPostTopicByTopicName(String topicName);
	
	public PostTopic createPostTopic(PostTopic topic);

	public PostTopic updatePostTopic(PostTopic topic);
	
	public boolean deletePostTopic(int id);
}
