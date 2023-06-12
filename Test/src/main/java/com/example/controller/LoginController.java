package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String loginPage() {
		return "login/login";
	}
	
	/** Redirect to user list screen */
	@PostMapping ("/login" )
	public String postLogin() {
	return "redirect:/user/list" ;
	}

}
