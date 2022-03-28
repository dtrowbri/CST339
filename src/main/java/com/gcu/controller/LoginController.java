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
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.AuthenticationBusinessServiceInterface;
import com.gcu.model.AuthenticationModel;




@Controller
@RequestMapping("/login")
public class LoginController {
		
		@Autowired private AuthenticationBusinessServiceInterface authenticationService;
	
		@GetMapping("/")
		public String display(Model model) {
			model.addAttribute("title", "Login Form");
			model.addAttribute("authenticationModel", new AuthenticationModel());
			
			return "login";
		}	
		
		@PostMapping("/authenticate")
		public String authenticate(@Valid AuthenticationModel authenticationModel, BindingResult bindingResult, Model model) {
			
			if(bindingResult.hasErrors()) {
				return "login";
			}
			if(authenticationService.Authenticate(authenticationModel)) {
				model.addAttribute("title", "Main Wall");
				return "mainWall";
			} else {
				model.addAttribute("title","Login Form");
				bindingResult.addError(new ObjectError("username", "Username or password is incorrect!"));
				return "login";
			}
		}
}
