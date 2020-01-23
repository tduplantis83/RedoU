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

import com.redou.entities.User;
import com.redou.entities.UserCurrentGoal;
import com.redou.services.UserCurrentGoalService;
import com.redou.services.UserService;

@RestController
@RequestMapping(path = "api/")
@CrossOrigin({ "*", "http://localhost:4444" })
public class UserCurrentGoalController {
	
	@Autowired
	private UserCurrentGoalService ucgSvc;
	
	@Autowired
	private UserService userSvc;
	
	@GetMapping("usercurrentgoal/id/{id}")
	public UserCurrentGoal getUserCurrentGoalById(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		UserCurrentGoal ucg = ucgSvc.getUserCurrentGoalById(id);
		if (ucg == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return ucg;
	}
	
	@GetMapping("usercurrentgoal/userid/{id}")
	public List<UserCurrentGoal>  getUserCurrentGoalByUserId(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		List<UserCurrentGoal> ucgs = ucgSvc.getUserCurrentGoalByUserId(id);
		if (ucgs.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return ucgs;
	}
	
	@GetMapping("usercurrentgoal/username/{username}")
	public List<UserCurrentGoal>  getUserCurrentGoalByUsername(@PathVariable String username, HttpServletRequest req, HttpServletResponse resp) {
		List<UserCurrentGoal> ucgs = ucgSvc.getUserCurrentGoalByUsername(username);
		if (ucgs.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return ucgs;
	}
	
	@PostMapping("usercurrentgoal/create")
	public UserCurrentGoal createUserCurrentGoal(@RequestBody UserCurrentGoal ucg, HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		try {
			User u = userSvc.getUserByUsernameExact(principal.getName());
			ucg.setUser(u);
			ucg = ucgSvc.createUserCurrentGoal(ucg);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(ucg.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			ucg = null;
		}

		return ucg;
	}
	
	@PutMapping("usercurrentgoal/update/{id}")
	public UserCurrentGoal updateUserCurrentGoal(@RequestBody UserCurrentGoal ucg, @PathVariable int id, HttpServletRequest req,
			HttpServletResponse resp, Principal principal) {
		try {
			// try to update the provided user
			User u = userSvc.getUserByUsernameExact(principal.getName());
			ucg.setUser(u);
			ucg = ucgSvc.updateUserCurrentGoal(ucg, id);
			if (ucg == null) {
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
			ucg = null;
		}

		return ucg;

	}
	
	@DeleteMapping("usercurrentgoal/delete/{id}")
	public void deleteUserCurrentGoal(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse resp) {

		try {
			if (ucgSvc.deleteUserCurrentGoal(id)) {
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
