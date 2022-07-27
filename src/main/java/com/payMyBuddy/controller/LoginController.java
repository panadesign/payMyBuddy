package com.payMyBuddy.controller;

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


	@GetMapping("/logout")
	public String logout() {
		return "/login";
	}


}
