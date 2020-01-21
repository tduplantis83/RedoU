package com.redou.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.redou.entities.PostReply;


@RunWith(SpringRunner.class)
@SpringBootTest
class PostReplyRepoTest {
	
	@Autowired
	private PostReplyRepo repo;
	
	@Test
	@DisplayName("test FindByID")
	void test1() {
		PostReply reply = repo.findById(1);
		assertEquals("There are several different types of intermittent fasting including: 16 hours fasting with 8 hours eating, One Meal A Day (OMAD). The general idea is to get your blood sugar down and prevent insulin spikes. In reality you can modify it so that it works best for you, but you should go for 16 hour fasts at a minimum. A lot of new research shows that this is the best way for humans to lose weight. It's actually thought that this is how our evolutionary ancestors ate (they didn't eat 3 meals a day, but instead ate when they were able to get food.", reply.getReplyContent());
	}
	
	@Test
	@DisplayName("test findByReplyUser_Id")
	void test2() {
		List<PostReply> reply = repo.findByReplyUser_Id(2);
		assertEquals("Intermittent Fasting", reply.get(0).getOriginalPost().getTitle());
	}

	@Test
	@DisplayName("test findByReplyUser_UsernameIgnoreCase")
	void test3() {
		List<PostReply> reply = repo.findByReplyUser_UsernameIgnoreCase("travIsD");
		assertEquals("Intermittent Fasting", reply.get(0).getOriginalPost().getTitle());
	}
	
	@Test
	@DisplayName("test findByOriginalPost_Id")
	void test4() {
		PostReply reply = repo.findByOriginalPost_Id(1);
		assertEquals("Travis", reply.getReplyUser().getFirstName());
	}
	
}
