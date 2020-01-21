package com.redou.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.redou.entities.Avatar;


@RunWith(SpringRunner.class)
@SpringBootTest
class AvatarRepoTest {

	@Autowired
	private AvatarRepo repo;
	
	@Test
	@DisplayName("test FindByID")
	void test1() {
		Avatar a = repo.findById(1);
		assertEquals(1, a.getAvatarGroup());
		assertEquals("Thin", a.getBodyType());
	}

	@Test
	@DisplayName("test findByAvatarGroup")
	void test2() {
		List<Avatar> a = repo.findByAvatarGroup(1);
		assertEquals(5, a.size());
		assertEquals("M", a.get(0).getSex());
	}
}
