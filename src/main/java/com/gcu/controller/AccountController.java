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

import com.gcu.business.AccountUpdateServiceInterface;
import com.gcu.business.AuthenticationBusinessServiceInterface;
import com.gcu.data.DataAccessInterface;
import com.gcu.data.UserDataService;
import com.gcu.model.AuthenticationModel;
import com.gcu.model.UserModel;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired private AuthenticationBusinessServiceInterface authenticationService;
	@Autowired private AccountUpdateServiceInterface updateService;
	@Autowired private UserDataService dai;
	
	private UserModel user;
	
	@GetMapping("/")
	public String displayModel(Model model) {
		// display login form view
		model.addAttribute("title", "Account Login");
		model.addAttribute("authenticationModel", new AuthenticationModel());
		return "account";		
	}
	
	@PostMapping("/AccountAuthentication")
	public String doAccountAuthentication(@Valid AuthenticationModel authenticationModel, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "account";
		}
		if(authenticationService.Authenticate(authenticationModel)) {
			user = dai.findByUserName(authenticationModel.getUsername());
			model.addAttribute("title", "Account Management");
			model.addAttribute("user", user);
			return "accountManagement";
		} else {
			model.addAttribute("title","Login Form");
			bindingResult.addError(new ObjectError("username", "Username or password is incorrect!"));
			return "account";
		}
	}
	
	@GetMapping("/editAccount")
	public String editAccount(Model model) {
		model.addAttribute("title", "Edit Account");
		model.addAttribute("user", user);
		return "editAccount";
	}
	
	@PostMapping("/updateUserInfo")
	public String updateUserInfo(@Valid UserModel userModel, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "editAccount";
		}
		if (updateService.updateUser(userModel)) {
			model.addAttribute("title", "Account Management");
			model.addAttribute("user", userModel);
			return "accountManagement";
		} else {
			model.addAttribute("title", "Edit Account");
			bindingResult.addError(new ObjectError("username", "Failed to update Account. Please contact an admin"));
			return "editAccount";
		}
		
	}
}
