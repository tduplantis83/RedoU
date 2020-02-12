package com.redou.controllers;

import java.security.Principal;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redou.entities.User;
import com.redou.entities.UserAvatar;
import com.redou.services.UserAvatarService;
import com.redou.services.UserService;

@RestController
@RequestMapping(path = "api/")
@CrossOrigin({ "*", "http://localhost:4444" })
public class UserAvatarController {

	@Autowired
	private UserAvatarService uaSvc;

	@Autowired
	private UserService userSvc;

	@GetMapping("useravatar/id/{id}")
	public UserAvatar getUserAvatarById(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		UserAvatar ua = uaSvc.getById(id);
		if (ua == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return ua;
	}

	@GetMapping("useravatar/userid/{id}")
	public List<UserAvatar> getUserAvatarByUserId(@PathVariable int id, HttpServletRequest req,
			HttpServletResponse resp) {
		List<UserAvatar> uas = uaSvc.getByUserId(id);
		if (uas.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return uas;
	}

	@GetMapping("useravatar/username/{username}")
	public List<UserAvatar> getUserAvatarByUserId(@PathVariable String username, HttpServletRequest req,
			HttpServletResponse resp) {
		List<UserAvatar> uas = uaSvc.getByUsername(username);
		if (uas.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return uas;
	}

	@PostMapping("useravatar/create/{avatarGroupId}")
	public List<UserAvatar> createUserAvatars(@PathVariable int avatarGroupId, HttpServletRequest req,
			HttpServletResponse resp, Principal principal) {
		System.out.println("*************CONTROLLER - Avatar Group ID " + avatarGroupId);
		List<UserAvatar> userAvatars = new ArrayList<>();
		try {
			User u = userSvc.getUserByUsernameExact(principal.getName());
			userAvatars = uaSvc.createUserAvatar(u.getId(), avatarGroupId);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(userAvatars.get(0).getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			userAvatars = null;
		}

		return userAvatars;
	}

	@PutMapping("useravatar/update/{avatarGroupId}")
	public List<UserAvatar> updateUserAvatars(@PathVariable int avatarGroupId, HttpServletRequest req,
			HttpServletResponse resp, Principal principal) {
		List<UserAvatar> userAvatars = new ArrayList<>();
		try {
			// try to update the provided user
			User u = userSvc.getUserByUsernameExact(principal.getName());
			userAvatars = uaSvc.updateUserAvatar(u.getId(), avatarGroupId);
			if (userAvatars.size() == 0) {
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
			userAvatars = null;
		}

		return userAvatars;

	}

	@PutMapping("useravatar/updatecurrent/{bodyType}")
	public UserAvatar updateUserCurrentAvatar(@PathVariable String bodyType, HttpServletRequest req,
			HttpServletResponse resp, Principal principal) {
		UserAvatar userAvatar = new UserAvatar();
		try {
			// try to update the provided user
			User u = userSvc.getUserByUsernameExact(principal.getName());
			userAvatar = uaSvc.updateUserCurrentAvatar(u.getId(), bodyType);
			if (userAvatar == null) {
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
			userAvatar = null;
		}

		return userAvatar;

	}

	@DeleteMapping("useravatar/delete/{userId}")
	public void deleteUserAvatar(@PathVariable int userId, HttpServletRequest req, HttpServletResponse resp) {

		try {
			if (uaSvc.deleteUserAvatar(userId)) {
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
