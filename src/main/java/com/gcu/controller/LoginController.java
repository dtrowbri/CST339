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
import com.gcu.business.ResetPasswordServiceInterface;
import com.gcu.model.AuthenticationModel;
import com.gcu.model.ResetPasswordModel;



@Controller
@RequestMapping("/login")
public class LoginController {
		
		@Autowired private AuthenticationBusinessServiceInterface authenticationService;
		@Autowired private ResetPasswordServiceInterface resetPasswordService;
	
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
				return "redirect:/";
			} else {
				model.addAttribute("title","Login Form");
				bindingResult.addError(new ObjectError("username", "Username or password is incorrect!"));
				return "login";
			}
		}
		
		@GetMapping("/ResetPassword")
		public String resetPassword(Model model) {
			model.addAttribute("title", "Reset Password");
			model.addAttribute("resetPasswordModel", new ResetPasswordModel());
			
			return "passwordReset";
		}
		
		@PostMapping("/doResetPassword")
		public String doResetPassword(@Valid ResetPasswordModel resetPasswordModel, BindingResult bindingResult, Model model) {
			
			if(bindingResult.hasErrors()) {
				return "passwordReset";
			}
			
			if(authenticationService.Authenticate(new AuthenticationModel(resetPasswordModel.getUsername(), resetPasswordModel.getCurrentPassword()))) {
				if(resetPasswordModel.isNewPasswordMatch()) {
					if(resetPasswordService.ResetPassword(resetPasswordModel)) {
						model.addAttribute("title", "Login Form");
						return "redirect:/login/";
					} else {
						model.addAttribute("title", "Reset Password");
						bindingResult.addError(new ObjectError("username", "Failed to update password. Please contact an admin"));
						return "passwordReset";
					}
				} else {
					model.addAttribute("title", "Reset Password");
					bindingResult.addError(new ObjectError("verifyPassword", "Passwords do not match"));
					return "passwordReset";
				}
			} else {
				model.addAttribute("title", "Reset Password");
				bindingResult.addError(new ObjectError("username", "Username or password are incorrect"));
				return "passwordReset";
			}
		}
}
