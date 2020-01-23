package com.redou.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redou.entities.PostTopic;
import com.redou.repositories.PostTopicRepo;

@Service
public class PostTopicServiceImpl implements PostTopicService{
	
	@Autowired
	private PostTopicRepo repo;

	@Override
	public PostTopic getPostTopicById(int id) {
		return repo.findById(id);
	}

	@Override
	public PostTopic getPostTopicByTopicName(String topicName) {
		return repo.findByTopicNameIgnoreCase(topicName);
	}
	
	@Override
	public List<PostTopic> getAllPostTopics() {
		return repo.findAll();
	}

	@Override
	public PostTopic createPostTopic(PostTopic topic) {
		topic.setDateCreated(LocalDate.now());
		topic.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(topic);
	}

	@Override
	public PostTopic updatePostTopic(PostTopic topic, int id) {
		PostTopic toUpdate = repo.findById(topic.getId());
		
		toUpdate.setTopicName(topic.getTopicName());
		toUpdate.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(toUpdate);
	}

	@Override
	public boolean deletePostTopic(int id) {
		try {
			PostTopic toDelete = repo.findById(id);

			repo.delete(toDelete);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	

}
