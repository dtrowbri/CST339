package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gcu.data.PostDataAccessService;
import com.gcu.data.DataAccessInterface;

import com.gcu.model.PostModel;
import com.gcu.model.UserModel;

public class PostService implements PostServiceInterface {

	@Autowired 
	@Qualifier("PostDataAccess")
	DataAccessInterface service;
	
	@Autowired
	@Qualifier("UserDataAccess")
	DataAccessInterface userService;
	
	public PostService() {
	}
	
	
	@Override
	public List<PostModel> findAll() {
		List<PostModel> posts = service.findAll();
		for(PostModel post: posts){
			UserModel user = (UserModel)userService.findById(post.getUserId());
			if(user != null) { 
				post.setUsername(user.getUsername());
			}
		}
		
		/*
		 * Should be optimized to search for parent using binary search
		 * Just sort the Arraylist by parentid, or even better retrieved from database sorted
		 * This would switch the sorting from O(n^2) to O(log(n));
		 */
		for(PostModel post : posts) {
			if(post.getParentPostId() > 0) {
				for(PostModel parent : posts){
					if(parent.getId() == post.getParentPostId()) {
						parent.addResponse(post);
					}
				}
			}
		}
		List<PostModel> parentPosts = new ArrayList<PostModel>();
		for(PostModel post : posts) {
			if(post.getParentPostId() == 0) {
				parentPosts.add(post);
			}
		}
		
		return parentPosts;
	}

	@Override
	public PostModel findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(@Valid PostModel post) {
		return service.create(post);
	}

	@Override
	public boolean delete(PostModel t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(PostModel t) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
