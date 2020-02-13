package com.redou.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.redou.entities.Image;



@ExtendWith(SpringExtension.class)
@SpringBootTest
class ImageRepoTest {

	@Autowired
	private ImageRepo repo;
	
	@Test
	@DisplayName("test FindByID")
	void test1() {
		Image img = repo.findById(1);
		assertEquals("https://i.imgur.com/puVjtA9.jpg", img.getImageUrl());
	}
	
	@Test
	@DisplayName("test findByUser_Id")
	void test2() {
		List<Image> imgs = repo.findByUser_Id(2);
		assertEquals("https://i.imgur.com/puVjtA9.jpg", imgs.get(0).getImageUrl());
	}

	@Test
	@DisplayName("test findByUser_UsernameIgnoreCase")
	void test3() {
		List<Image> imgs = repo.findByUser_UsernameIgnoreCase("TRAVisd");
		assertEquals("https://i.imgur.com/puVjtA9.jpg", imgs.get(0).getImageUrl());
	}
}
