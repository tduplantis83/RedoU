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

import com.redou.entities.MealType;
import com.redou.services.MealTypeService;

@RestController
//@RequestMapping(path = "api/")
@CrossOrigin({ "*", "http://localhost:4444" })
public class MealTypeController {
	
	@Autowired
	private MealTypeService svc;
	
	@GetMapping("mealtype/id/{id}")
	public MealType getMealTypeById(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		MealType mt = svc.getMealTypeById(id);
		if (mt == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return mt;
	}

	@GetMapping("mealtype/mealtypename/{mealTypeName}")
	public MealType getMealTypeByMealTypeName(@PathVariable String mealTypeName, HttpServletRequest req, HttpServletResponse resp) {
		MealType mt = svc.getMealTypeByMealTypeName(mealTypeName);
		if (mt == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return mt;
	}
	
	@GetMapping("mealtype/all")
	public List<MealType> getallMealTypes(HttpServletRequest req, HttpServletResponse resp) {
		List<MealType> mts = svc.getAllMealTypes();
		if (mts.size() ==0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return mts;
	}
	
	@PostMapping("api/mealtype/create")
	public MealType createMealType(@RequestBody MealType mealType, HttpServletRequest req, HttpServletResponse resp) {
		try {
			mealType = svc.createMealType(mealType);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(mealType.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			mealType = null;
		}
		
		return mealType;
	}

	@PutMapping("api/mealtype/update/{id}")
	public MealType updateMealType(@RequestBody MealType mealType, @PathVariable int id, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			mealType = svc.updateMealType(mealType, id);
			if (mealType == null) {
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
			mealType = null;
		}

		return mealType;

	}

	@DeleteMapping("api/mealtype/delete/{id}")
	public void deleteMealType(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {

		try {
			if (svc.deleteMealType(id)) {
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
