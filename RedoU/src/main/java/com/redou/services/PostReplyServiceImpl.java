package com.redou.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.redou.entities.PostReply;
import com.redou.repositories.PostReplyRepo;

@Service
public class PostReplyServiceImpl implements PostReplyService{
	
	@Autowired
	private PostReplyRepo repo;

	@Override
	public PostReply getPostReplyById(int id) {
		return repo.findById(id);
	}

	@Override
	public List<PostReply> getPostReplyByReplyUserId(int replyUserId) {
		return repo.findByReplyUser_Id(replyUserId);
	}

	@Override
	public List<PostReply> getPostReplyByReplyUsername(String username) {
		return repo.findByReplyUser_UsernameIgnoreCase(username);
	}

	@Override
	public PostReply getPostReplyByOriginalPostId(int originalPostId) {
		return repo.findByOriginalPost_Id(originalPostId);
	}
	
	@Override
	public PostReply getPostReplyByOriginalPostUserId(int originalPostUserId) {
		return repo.findByOriginalPost_User_Id(originalPostUserId);
	}

	@Override
	public PostReply getPostReplyByOriginalPostUsername(String username) {
		return repo.findByOriginalPost_User_UsernameIgnoreCase(username);
	}

	@Override
	public PostReply createPostReply(PostReply reply) {
		reply.setDateCreated(LocalDate.now());
		reply.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(reply);
	}

	@Override
	public PostReply updatePostReply(PostReply reply, int id) {
		PostReply toUpdate = repo.findById(id);
		
		toUpdate.setReplyUser(reply.getReplyUser());
		toUpdate.setOriginalPost(reply.getOriginalPost());
		toUpdate.setReplyContent(reply.getReplyContent());
		toUpdate.setDateCreated(reply.getDateCreated());
		toUpdate.setDateUpdated(LocalDate.now());
		
		return repo.saveAndFlush(toUpdate);
	}

	@Override
	public boolean deletePostReply(int id) {
		try {
			PostReply toDelete = repo.findById(id);

			repo.delete(toDelete);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	
	
	

}
