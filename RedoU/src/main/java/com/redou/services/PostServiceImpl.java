package com.redou.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redou.entities.Post;
import com.redou.repositories.PostRepo;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepo repo;

	@Override
	public Post getPostById(int id) {
		return repo.findById(id);
	}

	@Override
	public List<Post> getPostByUserId(int userId) {
		return repo.findByUser_Id(userId);
	}

	@Override
	public List<Post> getPostByUsername(String username) {
		return repo.findByUser_UsernameIgnoreCase(username);
	}

	@Override
	public List<Post> getPostByPostTopic(String postTopic) {
		return repo.findByPostTopic_TopicNameIgnoreCase(postTopic);
	}

	@Override
	public List<Post> getPostByTitleLike(String title) {
		return repo.findByTitleIgnoreCaseContaining(title);
	}

	@Override
	public List<Post> getPostByContentLike(String content) {
		return repo.findByContentIgnoreCaseContaining(content);
	}
	
	@Override
	public List<Post> getAllPosts() {
		return repo.findAll();
	}

	@Override
	public Post createPost(Post post) {
		post.setDateCreated(LocalDate.now());
		post.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(post);
	}

	@Override
	public Post updatePost(Post post, int id) {
		Post toUpdate = repo.findById(id);
		
		if(post.getUser() != null) {
			toUpdate.setUser(post.getUser());
		}
		if(post.getPostTopic() != null) {
			toUpdate.setPostTopic(post.getPostTopic());
		}
		if(post.getTitle() != null) {
			toUpdate.setTitle(post.getTitle());
		}
		if(post.getContent() != null) {
			toUpdate.setContent(post.getContent());
		}
		toUpdate.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(toUpdate);
	}

	@Override
	public boolean deletePost(int id) {
		try {
			Post toDelete = repo.findById(id);

			repo.delete(toDelete);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
