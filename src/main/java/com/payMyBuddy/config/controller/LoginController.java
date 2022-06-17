package com.payMyBuddy.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.RolesAllowed;

@Controller
public class LoginController {

	@RolesAllowed("USER")
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@RolesAllowed("USER")
	@GetMapping("/transfer")
	public String transfer() {
		return "transfer";
	}

}
