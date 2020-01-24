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

import com.redou.entities.BodyMeasurementMetric;
import com.redou.entities.User;
import com.redou.services.BodyMeasurementMetricService;
import com.redou.services.UserService;

@RestController
@RequestMapping(path = "api/")
@CrossOrigin({ "*", "http://localhost:4444" })
public class BodyMeasurementMetricController {

	@Autowired
	private BodyMeasurementMetricService bmmSvc;

	@Autowired
	private UserService userSvc;

	@GetMapping("bodymeasurementmetric/id/{id}")
	public BodyMeasurementMetric getBodyMeasurementMetricById(@PathVariable int id, HttpServletRequest req,
			HttpServletResponse resp) {
		BodyMeasurementMetric measurement = bmmSvc.getBodyMeasurementMetricById(id);
		if (measurement == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return measurement;
	}

	@GetMapping("bodymeasurementmetric/userid/{id}")
	public List<BodyMeasurementMetric> getBodyMeasurementMetricByUserId(@PathVariable int id,
			HttpServletRequest req, HttpServletResponse resp) {
		List<BodyMeasurementMetric> measurements = bmmSvc.getBodyMeasurementMetricByUserId(id);
		if (measurements.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return measurements;
	}

	@GetMapping("bodymeasurementmetric/username/{username}")
	public List<BodyMeasurementMetric> getBodyMeasurementMetricByUsername(@PathVariable String username,
			HttpServletRequest req, HttpServletResponse resp) {
		List<BodyMeasurementMetric> measurements = bmmSvc.getBodyMeasurementMetricByUsername(username);
		if (measurements.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return measurements;
	}

	@PostMapping("bodymeasurementmetric/create")
	public BodyMeasurementMetric createBodyMeasurementMetric(
			@RequestBody BodyMeasurementMetric measurement, HttpServletRequest req, HttpServletResponse resp,
			Principal principal) {
		try {
			User u = userSvc.getUserByUsernameExact(principal.getName());
			measurement.setUser(u);
			measurement = bmmSvc.createBodyMeasurementMetric(measurement);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(measurement.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			measurement = null;
		}

		return measurement;
	}

	@PutMapping("bodymeasurementmetric/update/{id}")
	public BodyMeasurementMetric updateBodyMeasurementMetric(@RequestBody BodyMeasurementMetric measurement, @PathVariable int id,
			HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		try {
			User u = userSvc.getUserByUsernameExact(principal.getName());
			measurement.setUser(u);
			measurement = bmmSvc.updateBodyMeasurementMetric(measurement, id);
			if (measurement == null) {
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
			measurement = null;
		}

		return measurement;

	}

	@DeleteMapping("bodymeasurementmetric/delete/{id}")
	public void deleteBodyMeasurementMetric(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		try {
			if (bmmSvc.deleteBodyMeasurementMetric(id)) {
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
