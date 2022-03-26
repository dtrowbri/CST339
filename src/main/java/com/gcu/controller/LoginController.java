package com.gcu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
			
			if(authenticationService.Authenticate(authenticationModel, bindingResult)) {
				model.addAttribute("title", "Main Wall");
				return "mainWall";
			} else {
				model.addAttribute("title","Login Form");
				return "login";
			}
		}
}
