package com.payMyBuddy.controller;

import com.payMyBuddy.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@Autowired
	UserAccountService userAccountService;

	@GetMapping({"/login", "/"})
	public String login() {
		return "/login";
	}

	@GetMapping("/transfer")
	public String transfer() {
		return "/transfer";
	}

	@GetMapping("/logout")
	public String logout() {
		return "/login";
	}


}
