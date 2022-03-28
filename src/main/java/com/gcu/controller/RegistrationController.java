package com.gcu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.RegistrationBusinessServiceInterface;
import com.gcu.model.UserModel;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired private RegistrationBusinessServiceInterface registrationService;
	
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
			return "registration";
		}
		
		// verify that the entered username is available
		if (registrationService.isUserNameTaken(userModel)) {
			model.addAttribute("title", "Confirmation");
			model.addAttribute("user", userModel);
			return "confirmation";
		} else {
			bindingResult.addError(new ObjectError("username", "The username you entered is already taken."));
			model.addAttribute("title", "Registration Form");
			return "registration";
		}
		
	}

}
