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

import com.redou.entities.DailyCaloricIntake;
import com.redou.entities.User;
import com.redou.services.DailyCaloricIntakeService;
import com.redou.services.UserService;

@RestController
@RequestMapping(path = "api/")
@CrossOrigin({ "*", "http://localhost:4444" })
public class DailyCaloricIntakeController {

	@Autowired
	private DailyCaloricIntakeService dciSvc;

	@Autowired
	private UserService userSvc;

	@GetMapping("DailyCaloricIntake/id/{id}")
	public DailyCaloricIntake getDailyCaloricIntakeById(@PathVariable int id, HttpServletRequest req,
			HttpServletResponse resp) {
		DailyCaloricIntake intake = dciSvc.getDailyCaloricIntakeById(id);
		if (intake == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return intake;
	}

	@GetMapping("DailyCaloricIntake/userid/{id}")
	public List<DailyCaloricIntake> getDailyCaloricIntakeByUserId(@PathVariable int id,
			HttpServletRequest req, HttpServletResponse resp) {
		List<DailyCaloricIntake> intakes = dciSvc.getDailyCaloricIntakeByUserId(id);
		if (intakes.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return intakes;
	}

	@GetMapping("DailyCaloricIntake/username/{username}")
	public List<DailyCaloricIntake> getDailyCaloricIntakeByUsername(@PathVariable String username,
			HttpServletRequest req, HttpServletResponse resp) {
		List<DailyCaloricIntake> intakes = dciSvc.getDailyCaloricIntakeByUsername(username);
		if (intakes.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return intakes;
	}

	@PostMapping("DailyCaloricIntake/create")
	public DailyCaloricIntake createDailyCaloricIntake(
			@RequestBody DailyCaloricIntake intake, HttpServletRequest req, HttpServletResponse resp,
			Principal principal) {
		try {
			User u = userSvc.getUserByUsernameExact(principal.getName());
			intake.setUser(u);
			intake = dciSvc.createDailyCaloricIntake(intake);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(intake.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			intake = null;
		}

		return intake;
	}

	@PutMapping("DailyCaloricIntake/update/{id}")
	public DailyCaloricIntake updateDailyCaloricIntake(@RequestBody DailyCaloricIntake intake, @PathVariable int id,
			HttpServletRequest req, HttpServletResponse resp) {
		try {
			intake = dciSvc.updateDailyCaloricIntake(intake, id);
			if (intake == null) {
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
			intake = null;
		}

		return intake;

	}

	@DeleteMapping("DailyCaloricIntake/delete/{id}")
	public void deleteDailyCaloricIntake(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		try {
			if (dciSvc.deleteDailyCaloricIntake(id)) {
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
