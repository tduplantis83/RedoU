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

import com.redou.entities.DailyExerciseCaloricDeficit;
import com.redou.entities.User;
import com.redou.services.DailyExerciseCaloricDeficitService;
import com.redou.services.UserService;

@RestController
@RequestMapping(path = "api/")
@CrossOrigin({ "*", "http://localhost:4444" })
public class DailyExerciseCaloricDeficitController {

	@Autowired
	private DailyExerciseCaloricDeficitService decdSvc;

	@Autowired
	private UserService userSvc;

	@GetMapping("dailyexercisecaloricdeficit/id/{id}")
	public DailyExerciseCaloricDeficit getDailyExerciseCaloricDeficitById(@PathVariable int id, HttpServletRequest req,
			HttpServletResponse resp) {
		DailyExerciseCaloricDeficit deficit = decdSvc.getDailyExerciseCaloricDeficitById(id);
		if (deficit == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return deficit;
	}

	@GetMapping("dailyexercisecaloricdeficit/userid/{id}")
	public List<DailyExerciseCaloricDeficit> getDailyExerciseCaloricDeficitByUserId(@PathVariable int id,
			HttpServletRequest req, HttpServletResponse resp) {
		List<DailyExerciseCaloricDeficit> deficits = decdSvc.getDailyExerciseCaloricDeficitByUserId(id);
		if (deficits.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return deficits;
	}

	@GetMapping("dailyexercisecaloricdeficit/username/{username}")
	public List<DailyExerciseCaloricDeficit> getDailyExerciseCaloricDeficitByUsername(@PathVariable String username,
			HttpServletRequest req, HttpServletResponse resp) {
		List<DailyExerciseCaloricDeficit> deficits = decdSvc.getDailyExerciseCaloricDeficitByUsername(username);
		if (deficits.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return deficits;
	}

	@PostMapping("dailyexercisecaloricdeficit/create")
	public DailyExerciseCaloricDeficit createDailyExerciseCaloricDeficit(
			@RequestBody DailyExerciseCaloricDeficit deficit, HttpServletRequest req, HttpServletResponse resp,
			Principal principal) {
		try {
			User u = userSvc.getUserByUsernameExact(principal.getName());
			deficit.setUser(u);
			deficit = decdSvc.createDailyExerciseCaloricDeficit(deficit);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(deficit.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			deficit = null;
		}

		return deficit;
	}

	@PutMapping("dailyexercisecaloricdeficit/update/{id}")
	public DailyExerciseCaloricDeficit updateDailyExerciseCaloricDeficit(@RequestBody DailyExerciseCaloricDeficit deficit, @PathVariable int id,
			HttpServletRequest req, HttpServletResponse resp) {
		try {
			deficit = decdSvc.updateDailyExerciseCaloricDeficit(deficit, id);
			if (deficit == null) {
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
			deficit = null;
		}

		return deficit;

	}

	@DeleteMapping("dailyexercisecaloricdeficit/delete/{id}")
	public void deleteDailyExerciseCaloricDeficit(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		try {
			if (decdSvc.deleteDailyExerciseCaloricDeficit(id)) {
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
