package com.payMyBuddy.config.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class SignupController {
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
}
