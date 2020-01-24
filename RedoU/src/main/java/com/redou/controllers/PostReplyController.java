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
import org.springframework.web.bind.annotation.RestController;

import com.redou.entities.PostReply;
import com.redou.entities.User;
import com.redou.services.PostReplyService;
import com.redou.services.UserService;

@RestController
//@RequestMapping(path = "api/")
@CrossOrigin({ "*", "http://localhost:4444" })
public class PostReplyController {
	
	@Autowired
	PostReplyService prSvc;
	
	@Autowired
	private UserService userSvc;
	
	@GetMapping("postreply/id/{id}")
	public PostReply getPostReplyById(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		PostReply pr = prSvc.getPostReplyById(id);
		if (pr == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return pr;
	}
	
	@GetMapping("postreply/replyuserid/{id}")
	public List<PostReply> getPostReplyByReplyUserId(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		List<PostReply> postreplies = prSvc.getPostReplyByReplyUserId(id);
		if (postreplies.size()==0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return postreplies;
	}
	
	@GetMapping("postreply/replyusername/{username}")
	public List<PostReply> getPostReplyByReplyUsername(@PathVariable String username, HttpServletRequest req, HttpServletResponse resp) {
		List<PostReply> postreplies = prSvc.getPostReplyByReplyUsername(username);
		if (postreplies.size()==0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return postreplies;
	}
	
	@GetMapping("postreply/originalpostid/{id}")
	public List<PostReply>  getPostReplyByOriginalPostId(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		List<PostReply> postreplies= prSvc.getPostReplyByOriginalPostId(id);
		if (postreplies.size()==0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return postreplies;
	}
	
	@GetMapping("postreply/originalpostuserid/{id}")
	public List<PostReply>  getPostReplyByOriginalPostUserId(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		List<PostReply> postreplies = prSvc.getPostReplyByOriginalPostUserId(id);
		if (postreplies.size()==0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return postreplies;
	}
	
	@GetMapping("postreply/originalpostusername/{username}")
	public List<PostReply>  getPostReplyByOriginalPostUserId(@PathVariable String username, HttpServletRequest req, HttpServletResponse resp) {
		List<PostReply> postreplies = prSvc.getPostReplyByOriginalPostUsername(username);
		if (postreplies.size()==0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return postreplies;
	}
	
	@PostMapping("api/postreply/create")
	public PostReply createPostReply(@RequestBody PostReply reply, HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		try {
			User u = userSvc.getUserByUsernameExact(principal.getName());
			reply.setReplyUser(u);
			reply = prSvc.createPostReply(reply);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(reply.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			reply = null;
		}

		return reply;
	}
	
	@PostMapping("api/postreply/createReplyToReply/{id}")
	public PostReply createPostReplyToPostReply(@RequestBody PostReply reply, @PathVariable int id, HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		try {
			User u = userSvc.getUserByUsernameExact(principal.getName());
			reply.setReplyUser(u);
			reply = prSvc.createPostReplyToReply(reply, id);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(reply.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			reply = null;
		}
		
		return reply;
	}

	@PutMapping("api/postreply/update/{id}")
	public PostReply updatePostReply(@RequestBody PostReply reply, @PathVariable int id, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			reply = prSvc.updatePostReply(reply, id);
			if (reply == null) {
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
			reply = null;
		}

		return reply;

	}

	@DeleteMapping("api/postreply/delete/{id}")
	public void deletePostReply(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {

		try {
			if (prSvc.deletePostReply(id)) {
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
