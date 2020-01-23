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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redou.entities.Image;
import com.redou.entities.User;
import com.redou.services.ImageService;
import com.redou.services.UserService;

@RestController
@RequestMapping(path = "api/")
@CrossOrigin({ "*", "http://localhost:4444" })
public class ImageController {
	
	@Autowired
	private ImageService imgSvc;
	
	@Autowired
	private UserService userSvc;
	
	@GetMapping("image/id/{id}")
	public Image getImageById(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		Image img = imgSvc.getImageById(id);
		if (img == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return img;
	}
	
	@GetMapping("image/userid/{id}")
	public List<Image> getImageByUserId(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		List<Image> imgs = imgSvc.getImageByUserId(id);
		if (imgs.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return imgs;
	}
	
	@GetMapping("image/username/{username}")
	public List<Image> getImageByUsername(@PathVariable String username, HttpServletRequest req, HttpServletResponse resp) {
		List<Image> imgs = imgSvc.getImageByUsername(username);
		if (imgs.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return imgs;
	}
	
	@PostMapping("image/create")
	public Image createImage(@RequestBody Image img, HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		try {
			User u = userSvc.getUserByUsernameExact(principal.getName());
			img.setUser(u);
			img = imgSvc.createImage(img);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(img.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			img = null;
		}
		
		return img;
	}

	@PutMapping("image/update/{id}")
	public Image updateImage(@RequestBody Image img, @PathVariable int id, HttpServletRequest req,
			HttpServletResponse resp, Principal principal) {
		try {
			User u = userSvc.getUserByUsernameExact(principal.getName());
			img.setUser(u);
			img = imgSvc.updateImage(img, id);
			if (img == null) {
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
			img = null;
		}

		return img;

	}

	@DeleteMapping("image/delete/{id}")
	public void deleteImage(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		try {
			if (imgSvc.deleteImage(id)) {
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
