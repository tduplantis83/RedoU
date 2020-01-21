package com.redou.repositories;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.redou.entities.Post;
import com.redou.repositories.PostRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostRepoTest {
	
	@Autowired
	private PostRepo repo;
	
	@Test
	@DisplayName("test FindByID")
	void test1() {
		Post post = repo.findById(1);
		assertEquals("Intermittent Fasting", post.getTitle());
	}

	@Test
	@DisplayName("test findByUser_Id")
	void test2() {
		List<Post> posts = repo.findByUser_Id(3);
		assertEquals("Emily", posts.get(0).getUser().getFirstName());
	}
	
	@Test
	@DisplayName("test findByUser_UsernameIgnoreCase")
	void test3() {
		List<Post> posts = repo.findByUser_UsernameIgnoreCase("EMILYd");
		assertEquals("Intermittent Fasting", posts.get(0).getTitle());
	}
	
	@Test
	@DisplayName("test findByPostTopic_TopicNameIgnoreCase")
	void test4() {
		List<Post> posts = repo.findByPostTopic_TopicNameIgnoreCase("DIET");
		assertEquals("Emily", posts.get(0).getUser().getFirstName());
	}
	
	@Test
	@DisplayName("test findByTitleIgnoreCaseContaining")
	void test5() {
		List<Post> posts = repo.findByTitleIgnoreCaseContaining("MITTENT FaST");
		assertEquals("Emily", posts.get(0).getUser().getFirstName());
	}
	
	@Test
	@DisplayName("test findByContentIgnoreCaseContaining")
	void test6() {
		List<Post> posts = repo.findByContentIgnoreCaseContaining("cURIous");
		assertEquals("Emily", posts.get(0).getUser().getFirstName());
	}
	
	
}
