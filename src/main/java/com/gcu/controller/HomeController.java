package com.gcu.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

import java.util.List;
import java.util.ArrayList;


@Controller
@RequestMapping("/")
public class HomeController {

	final String imageDir = "src/main/resources/static/images/";
	
	@GetMapping("")
	public String display(Model model) {
		
		List<PostModel> posts = new ArrayList<PostModel>();
		PostModel firstPost = new PostModel();
		firstPost.setImageLocation("/images/20220320144833tumblr_na1mmnDx9i1txue1qo1_540.jpg");
		firstPost.setUsername("dtrowbri");
		firstPost.setPostedOn(LocalDateTime.now());
		
		PostModel firstResponse = new PostModel();
		firstResponse.setImageLocation("/images/20220320151633FirstPost.JPG");
		firstResponse.setUsername("Daniel");
		firstResponse.setPostedOn(LocalDateTime.now());
		firstPost.addResponse(firstResponse);
		
		PostModel secondResponse = new PostModel();
		secondResponse.setImageLocation("/images/20220320152658o-when-you-praise-the-sun-just-right-imgur-52363068.png");
		secondResponse.setUsername("Erick");
		secondResponse.setPostedOn(LocalDateTime.now());
		firstPost.addResponse(secondResponse);
		
		PostModel secondPost = new PostModel();
		secondPost.setImageLocation("/images/20220320153008index2.jpg");
		secondPost.setUsername("MemeLord");
		secondPost.setPostedOn(LocalDateTime.now());

		posts.add(firstPost);
		posts.add(secondPost);
		
		model.addAttribute("memes", posts);
		model.addAttribute("title", "Main Wall");
		
		return "mainWall";
	}
	
	@PostMapping("/postMeme")
	public String postMeme(@RequestParam("meme") MultipartFile meme, RedirectAttributes attributes) {
		
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
		

		
		System.out.println(filename);
		return "redirect:/";
	}
	
	@PostMapping("/postLike")
	public String postLike() {
		return "redirect:/";
	}
	
	@PostMapping("/postDislike")
	public String postDislike() {
		return "redirect:/";
	}
	
	@PostMapping("/postShare")
	public String postShare() {
		return "redirect:/"; 
	}
}
