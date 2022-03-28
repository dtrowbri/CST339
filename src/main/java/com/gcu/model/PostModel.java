package com.gcu.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PostModel {

	 long id;
	 String username;
	 List<PostModel> responses;
	 String ImageLocation;
	 int numberOfLikes;
	 int numberOfDislikes;
	 LocalDateTime postedOn;
	
	public PostModel() {
		this.responses = new ArrayList<PostModel>();
	}
	
	public PostModel(String username, String ImageLocation, LocalDateTime postedOn) {
		this.responses = new ArrayList<PostModel>();
		this.setUsername(username);
		this.setImageLocation(ImageLocation);
		this.setNumberOfLikes(0);
		this.setNumberOfDislikes(0);
		this.setPostedOn(postedOn);
	}
	
	public PostModel(long id, String username, String ImageLocation, int numberOfLikes, int numberOfDislikes, LocalDateTime postedOn) {
		this.responses = new ArrayList<PostModel>();
		this.setId(id);
		this.setUsername(username);
		this.setImageLocation(ImageLocation);
		this.setNumberOfLikes(numberOfLikes);
		this.setNumberOfDislikes(numberOfDislikes);
		this.setPostedOn(postedOn);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<PostModel> getResponses() {
		return responses;
	}

	public void setResponses(List<PostModel> responses) {
		this.responses = responses;
	}
	
	public void addResponse(PostModel post) {
		this.responses.add(post);
	}

	public String getImageLocation() {
		return ImageLocation;
	}

	public void setImageLocation(String imageLocation) {
		ImageLocation = imageLocation;
	}

	public int getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	public int getNumberOfDislikes() {
		return numberOfDislikes;
	}

	public void setNumberOfDislikes(int numberOfDislikes) {
		this.numberOfDislikes = numberOfDislikes;
	}

	public LocalDateTime getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(LocalDateTime postedOn) {
		this.postedOn = postedOn;
	}
	
	public String getFormattedDate() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/YYYY HH:mm");
		String timePosted = dateTimeFormatter.format(this.getPostedOn());
		return timePosted;
	}
}
