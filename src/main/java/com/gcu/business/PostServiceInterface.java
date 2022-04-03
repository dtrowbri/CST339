package com.gcu.business;

import java.util.List;
import com.gcu.model.PostModel;

public interface PostServiceInterface {

	public List<PostModel> findAll();
	public PostModel findById(int id);
	public boolean create(PostModel postModel);
	public boolean delete(PostModel postModel);
	public boolean update(PostModel postModel);
}
