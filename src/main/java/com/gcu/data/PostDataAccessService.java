package com.gcu.data;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import com.gcu.model.PostModel;

@Service("PostDataAccess")
public class PostDataAccessService implements DataAccessInterface<PostModel> {

	@Autowired javax.sql.DataSource dataSource;
	@Autowired JdbcTemplate jdbcTemplateObject;
	
	public PostDataAccessService(javax.sql.DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<PostModel> findAll() {
		String sql = "SELECT * FROM POSTS ORDER BY `POSTEDON` DESC";
		List<PostModel> posts = new ArrayList<PostModel>();
		try {
			SqlRowSet srs = this.jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next()) {
				posts.add(new PostModel(srs.getLong("PostId"),
						srs.getLong("parentPostId"),
						srs.getInt("UserId"),
						srs.getString("ImageLocation"),
						srs.getInt("NumberOfLikes"),
						srs.getInt("NumberOfDislikes"),
						srs.getTimestamp("PostedOn").toLocalDateTime()));
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return posts;
	}

	@Override
	public PostModel findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public PostModel findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(PostModel post) {
		String sql = "INSERT INTO POSTS (`PostId`, `UserId`, `ImageLocation`, `NumberOfLikes`, `NumberOfDislikes`, `PostedOn`, `ParentPostId`) values (null, ?,?,?,?,?,?)";
		
		Object[] params = new Object[] {post.getUserId(), post.getImageLocation(), post.getNumberOfLikes(), post.getNumberOfDislikes(), post.getPostedOn(), post.getParentPostId()};
		int[] dataTypes = new int[] {Types.INTEGER, Types.NVARCHAR, Types.INTEGER, Types.INTEGER, Types.TIMESTAMP, Types.INTEGER};
		int numOfRows = 0;
		
		try {
			numOfRows = this.jdbcTemplateObject.update(sql, params, dataTypes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(numOfRows == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean update(PostModel post) {
		String sql = "UPDATE POSTS SET `NumberOfLikes` = ?, `NumberOfDislikes` = ? WHERE `PostId` = ?";
		
		int numOfRows = 0;
		Object[] params = new Object[] {post.getNumberOfLikes(), post.getNumberOfDislikes(), post.getId()};
		int[] dataTypes = new int[] {Types.INTEGER, Types.INTEGER, Types.INTEGER};
		
		try {
			numOfRows = this.jdbcTemplateObject.update(sql, params, dataTypes);
		} catch(Exception e) {
			e.printStackTrace();
		}

		
		if(numOfRows == 1) {
			return true;
		} else {
			 return false;
		}
	}

	@Override
	public boolean delete(PostModel t) {
		// TODO Auto-generated method stub
		return false;
	}

}
