package com.redou.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.redou.entities.User;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepoTest {

	@Autowired
	private UserRepo repo;
	
	@Test
	@DisplayName("test FindByID")
	void test1() {
		User user = repo.findById(2);
		assertEquals("Travis", user.getFirstName());
	}
	
	@Test
	@DisplayName("test findByUsernameIgnoreCase")
	void test2() {
		User user = repo.findByUsernameIgnoreCase("tRAVISd");
		assertEquals("Travis", user.getFirstName());
	}
	
	@Test
	@DisplayName("test findByUsernameIgnoreCaseContaining")
	void test3() {
		List<User> users = repo.findByUsernameIgnoreCaseContaining("RAVIS");
		assertEquals(1, users.size());
		assertEquals("Travis", users.get(0).getFirstName());
	}
	
	@Test
	@DisplayName("test findByEmailIgnoreCaseContaining")
	void test4() {
		List<User> users = repo.findByEmailIgnoreCaseContaining("LANTIS83@GMAI");
		assertEquals(1, users.size());
		assertEquals("Travis", users.get(0).getFirstName());
	}
	
	@Test
	@DisplayName("test findByEnabled")
	void test5() {
		List<User> users = repo.findByEnabled(true);
		assertEquals(3, users.size());
		assertEquals("Travis", users.get(1).getFirstName());
	}
	
	@Test
	@DisplayName("test findByRoleIgnoreCase")
	void test6() {
		List<User> users = repo.findByRoleIgnoreCase("USER");
		assertEquals(2, users.size());
		assertEquals("Emily", users.get(1).getFirstName());
	}
	

}
