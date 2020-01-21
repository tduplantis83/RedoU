package com.redou.repositories;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.redou.entities.PostTopic;
import com.redou.entities.UserAvatar;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostTopicRepoTest {
	
	@Autowired
	private PostTopicRepo repo;

	@Test
	@DisplayName("test FindByID")
	void test1() {
		PostTopic topic = repo.findById(1);
		assertEquals("Diet", topic.getTopicName());
	}
	
	@Test
	@DisplayName("test findByTopicNameIgnoreCase")
	void test2() {
		PostTopic topic = repo.findByTopicNameIgnoreCase("Diet");
		assertEquals("2020-01-18", topic.getDateCreated().toString());
	}

}
