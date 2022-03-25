package com.gcu.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.UserModel;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	@GetMapping("/")
	public String displayModel(Model model) {
		
		// display login form view
		model.addAttribute("title", "Registration Form");
		model.addAttribute("userModel", new UserModel());
		return "registration";
		
	}

	@PostMapping("/registerUser")
	public String resgisterUser(@Valid UserModel userModel, BindingResult bindingResult, Model model) {
		
		// check for validation errors
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Registration Form");
			return "registration";
		}
		
		model.addAttribute("title", "Confirmation");
		model.addAttribute("user", userModel);
		
		return "confirmation";
		
	}

}
