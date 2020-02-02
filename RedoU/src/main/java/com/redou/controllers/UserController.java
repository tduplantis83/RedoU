package com.redou.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.redou.entities.User;
import com.redou.services.AuthService;
import com.redou.services.UserService;

@RestController
@CrossOrigin({ "*", "http://localhost:4444" })
public class UserController {

	@Autowired
	private UserService userSvc;

	@Autowired
	private AuthService authSvc;

	@GetMapping("users/id/{id}")
	public User getUserById(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		User user = userSvc.getUserById(id);
		if (user == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return user;
	}

	@GetMapping("users/username/exact/{username}")
	public User getUserByUsernameExact(@PathVariable String username, HttpServletRequest req,
			HttpServletResponse resp) {
		User user = userSvc.getUserByUsernameExact(username);
		if (user == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return user;
	}

	@GetMapping("users/username/{username}")
	public List<User> getUserByUsernameContaining(@PathVariable String username, HttpServletRequest req,
			HttpServletResponse resp) {
		List<User> users = userSvc.getUserByUsernameContaining(username);
		if (users.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return users;
	}

	@GetMapping("users/email/{email}")
	public List<User> getUserByEmailContaining(@PathVariable String email, HttpServletRequest req,
			HttpServletResponse resp) {
		List<User> users = userSvc.getUserByEmailContaining(email);
		if (users.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return users;
	}

	@GetMapping("api/users/enabled/{enabled}")
	public List<User> getUserByEnabled(@PathVariable boolean enabled, HttpServletRequest req,
			HttpServletResponse resp) {
		List<User> users = userSvc.getUserByEnabled(enabled);
		if (users.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return users;
	}

	@GetMapping("api/users/role/{role}")
	public List<User> getUserByRole(@PathVariable String role, HttpServletRequest req, HttpServletResponse resp) {
		List<User> users = userSvc.getUserByRole(role);
		if (users.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return users;
	}

	@GetMapping("api/users/all")
	public List<User> getAllUsers(HttpServletRequest req, HttpServletResponse resp) {
		List<User> users = userSvc.getAllUsers();
		if (users.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return users;
	}
	
	@GetMapping("api/users/getloggedinuser")
	public User getLoggedInUser(HttpServletResponse res, Principal principal) {
		User loggedInUser = userSvc.getUserByUsernameExact(principal.getName());
		if (loggedInUser == null) {
			res.setStatus(404);
		} 
		else {
			res.setStatus(200);
		}
		return loggedInUser;
	}

	@PostMapping("users/create")
	public User createUser(@RequestBody User user, HttpServletRequest req, HttpServletResponse resp) {
		try {
			user = authSvc.register(user);
			
			if(user != null) {
				// if successful, send 201
				resp.setStatus(201);
				// get the link to the created post
				// return that in the Location header
				StringBuffer url = req.getRequestURL();
				url.append("/").append(user.getId());
				resp.addHeader("Location", url.toString());
			}
			else {
				resp.setStatus(200);
				// return a Location header with 
				// registration error
				StringBuffer url = req.getRequestURL();
				url.append("/").append("registrationerror");
				resp.addHeader("Location", url.toString());
			}
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			user = null;
		}

		return user;
	}

	@PutMapping("api/users/update/{id}")
	public User updateUser(@RequestBody User user, @PathVariable int id, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			// try to update the provided user
			user = userSvc.updateUser(user, id);
			if (user == null) {
				resp.setStatus(404);
			} else {
				// if successful, send 200
				resp.setStatus(200);
			}
		} catch (Exception e) {
			// if update fails, return 404 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			user = null;
		}

		return user;

	}

	@PutMapping("api/users/disable/{id}")
	public User disableUser(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		User user = userSvc.getUserById(id);
		try {
			// try to update the provided user
			user.setEnabled(false);
			user = userSvc.updateUser(user, id);
			if (user == null) {
				resp.setStatus(404);
			} else {
				// if successful, send 200
				resp.setStatus(200);
			}
		} catch (Exception e) {
			// if update fails, return 404 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			user = null;
		}

		return user;

	}

	@PutMapping("api/users/enable/{id}")
	public User enableUser(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		User user = userSvc.getUserById(id);
		try {
			// try to update the provided user
			user.setEnabled(true);
			user = userSvc.updateUser(user, id);
			if (user == null) {
				resp.setStatus(404);
			} else {
				// if successful, send 200
				resp.setStatus(200);
			}
		} catch (Exception e) {
			// if update fails, return 404 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			user = null;
		}

		return user;

	}

	@DeleteMapping("api/users/delete/{id}")
	public void deleteUser(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse resp) {

		try {
			if (userSvc.deleteUser(id)) {
				// if successful, send 204 - no content to send back
				resp.setStatus(204);
			} else {
				// if false, id doesn't exist
				// return 404 - not found
				resp.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatus(400);
		}

	}

}
