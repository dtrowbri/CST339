package com.gcu.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.RegistrationModel;
import com.gcu.model.UserModel;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	@GetMapping("/")
	public String displayModel(Model model) {
		
		// display login form view
		model.addAttribute("title", "Registration Form");
		model.addAttribute("registrationModel", new RegistrationModel());
		return "registration";
		
	}

	@PostMapping("/registerUser")
	public String resgisterUser(@Valid RegistrationModel regModel, BindingResult bindingResult, Model model) {
		
		// check for validation errors
		if (bindingResult.hasErrors()) {
			model.addAttribute("title", "Registration Form");
			return "registration";
		}
		
		// convert user input from form into a UserModel object
		UserModel user = new UserModel(
					regModel.getUsername(), 
					regModel.getPassword(), 
					regModel.getfName(), 
					regModel.getlName(), 
					regModel.getEmail(), 
					regModel.getAddress(), 
					regModel.getPhone()
				);
		
		model.addAttribute("title", "Confirmation");
		model.addAttribute("user", user);
		
		return "confirmation";
		
	}

}
