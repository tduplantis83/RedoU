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

import com.redou.entities.Goal;
import com.redou.services.GoalService;

@RestController
//@RequestMapping(path = "api/")
@CrossOrigin({ "*", "http://localhost:4444" })
public class GoalController {
	
	@Autowired
	private GoalService svc;
	
	@GetMapping("goal/id/{id}")
	public Goal getGoalById(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		Goal goal = svc.getGoalById(id);
		if (goal == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return goal;
	}
	
	@GetMapping("goal/goalname/{goalName}")
	public Goal getGoalByGoalName(@PathVariable String goalName, HttpServletRequest req, HttpServletResponse resp) {
		Goal goal = svc.getGoalByGoalName(goalName);
		if (goal == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return goal;
	}
	
	@GetMapping("goal/all")
	public List<Goal> getAllGoal(HttpServletRequest req, HttpServletResponse resp) {
		List<Goal> goals = svc.getAllGoals();
		if (goals.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return goals;
	}
	
	@PostMapping("api/goal/create")
	public Goal createGoal(@RequestBody Goal g, HttpServletRequest req, HttpServletResponse resp) {
		try {
			g = svc.createGoal(g);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(g.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			g = null;
		}
		
		return g;
	}

	@PutMapping("api/goal/update/{id}")
	public Goal updateGoal(@RequestBody Goal g, @PathVariable int id, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			g = svc.updateGoal(g, id);
			if (g == null) {
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
			g = null;
		}

		return g;

	}

	@DeleteMapping("api/goal/delete/{id}")
	public void deleteGoal(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		try {
			if (svc.deleteGoal(id)) {
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
