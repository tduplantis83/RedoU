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

import com.redou.entities.Post;
import com.redou.entities.User;
import com.redou.services.PostService;
import com.redou.services.UserService;

@RestController
//@RequestMapping(path = "api/")
@CrossOrigin({ "*", "http://localhost:4444" })
public class PostController {
	
	@Autowired
	private PostService postSvc;
	
	@Autowired
	private UserService userSvc;
	
	@GetMapping("post/id/{id}")
	public Post getPostById(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		Post p = postSvc.getPostById(id);
		if (p == null) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return p;
	}

	@GetMapping("post/userid/{id}")
	public List<Post> getPostByUserId(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
		List<Post> posts = postSvc.getPostByUserId(id);
		if (posts.size()==0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return posts;
	}
	
	@GetMapping("post/username/{username}")
	public List<Post> getPostByUsername(@PathVariable String username, HttpServletRequest req, HttpServletResponse resp) {
		List<Post> posts = postSvc.getPostByUsername(username);
		if (posts.size()==0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return posts;
	}
	
	@GetMapping("post/posttopic/{posttopic}")
	public List<Post> getPostByPostTopic(@PathVariable String posttopic, HttpServletRequest req, HttpServletResponse resp) {
		List<Post> posts = postSvc.getPostByPostTopic(posttopic);
		if (posts.size()==0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return posts;
	}
	
	@GetMapping("post/title/{title}")
	public List<Post> getPostByPostTitle(@PathVariable String title, HttpServletRequest req, HttpServletResponse resp) {
		List<Post> posts = postSvc.getPostByTitleLike(title);
		if (posts.size()==0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return posts;
	}
	
	@GetMapping("post/content/{content}")
	public List<Post> getPostByPostContent(@PathVariable String content, HttpServletRequest req, HttpServletResponse resp) {
		List<Post> posts = postSvc.getPostByContentLike(content);
		if (posts.size()==0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return posts;
	}
	
	@GetMapping("post/all")
	public List<Post> getAllPosts(HttpServletRequest req, HttpServletResponse resp) {
		List<Post> posts = postSvc.getAllPosts();
		if (posts.size() == 0) {
			resp.setStatus(404);
		} else {
			resp.setStatus(200);
		}
		return posts;
	}
	
	@PostMapping("api/post/create")
	public Post createPost(@RequestBody Post post, HttpServletRequest req, HttpServletResponse resp, Principal principal) {
		try {
			User u = userSvc.getUserByUsernameExact(principal.getName());
			post.setUser(u);
			post = postSvc.createPost(post);
			// if successful, send 201
			resp.setStatus(201);
			// get the link to the created post
			// return that in the Location header
			StringBuffer url = req.getRequestURL();
			url.append("/").append(post.getId());
			resp.addHeader("Location", url.toString());
		} catch (Exception e) {
			// if creation fails, return 400 error
			e.printStackTrace();
			resp.setStatus(400);
			// set the returning post to null
			post = null;
		}

		return post;
	}

	@PutMapping("api/post/update/{id}")
	public Post updatePost(@RequestBody Post post, @PathVariable int id, HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			post = postSvc.updatePost(post, id);
			if (post == null) {
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
			post = null;
		}

		return post;

	}

	@DeleteMapping("api/post/delete/{id}")
	public void deletePost(@PathVariable Integer id, HttpServletRequest req, HttpServletResponse resp) {

		try {
			if (postSvc.deletePost(id)) {
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
