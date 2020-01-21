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
	public List<Post> getPostByTitle(String title) {
		return repo.findByTitleLikeIgnoreCase("%" + title + "%");
	}

	@Override
	public List<Post> getPostByContent(String content) {
		return repo.findByContentLikeIgnoreCase("%" + content + "%");
	}

	@Override
	public Post createPost(Post post) {
		post.setDateCreated(LocalDate.now());
		post.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(post);
	}

	@Override
	public Post updatePost(Post post) {
		Post toUpdate = repo.findById(post.getId());
		
		toUpdate.setUser(post.getUser());
		toUpdate.setPostTopic(post.getPostTopic());
		toUpdate.setTitle(post.getTitle());
		toUpdate.setContent(post.getContent());
		toUpdate.setDateCreated(post.getDateCreated());
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
