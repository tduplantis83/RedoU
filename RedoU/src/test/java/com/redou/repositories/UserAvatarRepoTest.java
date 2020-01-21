package com.redou.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.redou.entities.UserAvatar;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserAvatarRepoTest {
	
	@Autowired
	private UserAvatarRepo repo;

	@Test
	@DisplayName("test FindByID")
	void test1() {
		UserAvatar avatar = repo.findById(2);
		assertEquals("Average", avatar.getAvatar().getBodyType());
	}

	@Test
	@DisplayName("test findByUser_Id")
	void test2() {
		List<UserAvatar> avatars = repo.findByUser_Id(2);
		assertEquals(5, avatars.size());
		assertEquals("Average", avatars.get(1).getAvatar().getBodyType());
	}
}
