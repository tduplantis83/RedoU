package com.redou.controllers;

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

import com.redou.entities.Avatar;
import com.redou.services.AvatarService;

@RestController
//@RequestMapping(path = "api/")
@CrossOrigin({ "*", "http://localhost:4444" })
public class AvatarController {
	
	@Autowired
	private AvatarService aSvc;
	
	@GetMapping("avatar/id/{id}")
	public Avatar getAvatarById(@PathVariable int id, HttpServletRequest req,
			HttpServletResponse resp) {
		Avatar avatar = aSvc.getAvatarById(id);
		if (avatar == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return avatar;
	}
	
	@GetMapping("avatar/all")
	public List<Avatar> getAllAvatar(HttpServletRequest req,
			HttpServletResponse resp) {
		List<Avatar> avatars = aSvc.getAllAvatar();
		if (avatars.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return avatars;
	}
	
	@GetMapping("avatar/sex/{sex}")
	public List<Avatar> getAvatarBySex(@PathVariable String sex, HttpServletRequest req,
			HttpServletResponse resp) {
		List<Avatar> avatars = aSvc.getAvatarBySex(sex);
		if (avatars.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return avatars;
	}
	
	@GetMapping("avatar/bodytype/{bodyType}")
	public List<Avatar> getAvatarByBodyType(@PathVariable String bodyType, HttpServletRequest req,
			HttpServletResponse resp) {
		List<Avatar> avatars = aSvc.getAvatarByBodyType(bodyType);
		if (avatars.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return avatars;
	}
	
	@PostMapping("api/avatar/create")
	public Avatar createAvatar(
			@RequestBody Avatar avatar, HttpServletRequest req, HttpServletResponse resp) {
		try {
			avatar = aSvc.createAvatar(avatar);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(avatar.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			avatar = null;
		}

		return avatar;
	}

	@PutMapping("api/avatar/update/{id}")
	public Avatar updateAvatar(@RequestBody Avatar avatar, @PathVariable int id,
			HttpServletRequest req, HttpServletResponse resp) {
		try {
			avatar = aSvc.updateAvatar(avatar, id);
			if (avatar == null) {
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
			avatar = null;
		}

		return avatar;

	}

	@DeleteMapping("api/avatar/delete/{id}")
	public void deleteAvatar(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		try {
			if (aSvc.deleteAvatar(id)) {
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
