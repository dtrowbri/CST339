package com.gcu.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcu.model.PostModel;
import com.gcu.model.UserModel;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import com.gcu.business.PostService;
import com.gcu.business.PostServiceInterface;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired private PostServiceInterface service;
	
	final String imageDir = "src/main/resources/static/images/";
	
	@GetMapping("")
	public String display(Model model) {
		
		List<PostModel> postsFromDB = service.findAll();
		
		model.addAttribute("memes", postsFromDB);

		//Set Title for Main Page
		model.addAttribute("title", "Main Wall");
		
		return "mainWall";
	}
	
	@PostMapping("/postMeme")
	public String postMeme(@RequestParam("meme") MultipartFile meme, RedirectAttributes attributes, HttpServletRequest request) {
		
		UserModel user;
		if(request.getSession().getAttribute("user") != null) {
			user = (UserModel)request.getSession().getAttribute("user");
		} else {
			return "redirect:/login/";
		}
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		String timeNowStr = dateTimeFormatter.format(now);
		String filename = StringUtils.cleanPath(meme.getOriginalFilename());
		filename = timeNowStr + filename;
		String fileNameReferencePath = "/images/" + filename;
		
		try {
		    Path path = Paths.get(imageDir + filename);
			Files.copy(meme.getInputStream(), path);
			PostModel newPost = new PostModel("admin", fileNameReferencePath, now);
			System.out.println(fileNameReferencePath);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		PostModel post = new PostModel(user.getUserId(), fileNameReferencePath, now);
		boolean postSucceeded = service.create(post);
		System.out.println(postSucceeded);
		
		System.out.println(filename);
		return "redirect:/";
	}
	
	@PostMapping("/postLike")
	public String postLike(PostModel post) {
		post.setNumberOfLikes(post.getNumberOfLikes() + 1);
		service.update(post);
		return "redirect:/";
	}
	
	@PostMapping("/postDislike")
	public String postDislike(PostModel post) {
		post.setNumberOfDislikes(post.getNumberOfDislikes() + 1);
		service.update(post);
		return "redirect:/";
	}
	
	@PostMapping("/postShare")
	public String postShare() {
		return "redirect:/"; 
	}
}
