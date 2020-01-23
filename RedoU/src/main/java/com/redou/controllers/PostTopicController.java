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

import com.redou.entities.PostTopic;
import com.redou.services.PostTopicService;

@RestController
//@RequestMapping(path = "api/")
@CrossOrigin({ "*", "http://localhost:4444" })
public class PostTopicController {

	@Autowired
	private PostTopicService pSvc;

	@GetMapping("posttopic/id/{id}")
	public PostTopic getPostTopicById(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		PostTopic pt = pSvc.getPostTopicById(id);
		if (pt == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return pt;
	}

	@GetMapping("posttopic/topicname/{topicName}")
	public PostTopic getPostTopicById(@PathVariable String topicName, HttpServletRequest req,
			HttpServletResponse resp) {
		PostTopic pt = pSvc.getPostTopicByTopicName(topicName);
		if (pt == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return pt;
	}

	@GetMapping("posttopic/all")
	public List<PostTopic> getAllPostTopics(HttpServletRequest req,
			HttpServletResponse resp) {
		List<PostTopic> pts = pSvc.getAllPostTopics();
		if (pts.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return pts;
	}

	@PostMapping("api/posttopic/create")
	public PostTopic createPostTopic(@RequestBody PostTopic topic, HttpServletRequest req, HttpServletResponse resp) {
		try {
			topic = pSvc.createPostTopic(topic);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(topic.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			topic = null;
		}

		return topic;
	}

	@PutMapping("api/posttopic/update/{id}")
	public PostTopic updatePostTopic(@RequestBody PostTopic topic, @PathVariable int id, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			// try to update the provided user
			topic = pSvc.updatePostTopic(topic, id);
			if (topic == null) {
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
			topic = null;
		}

		return topic;

	}

	@DeleteMapping("api/posttopic/delete/{id}")
	public void deletePostTopic(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse resp) {

		try {
			if (pSvc.deletePostTopic(id)) {
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
