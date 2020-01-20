package com.redou.services;

import com.redou.entities.PostTopic;

public interface PostTopicService {
	public PostTopic getPostTopicById(int id);

	public PostTopic getPostTopicByTopicName(String topicName);
}
