package com.PayMyBuddy.PayMyBuddy.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.RolesAllowed;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/transfer")
	public String transfer() {
		return "transfer";
	}

	@RequestMapping("/*")
	@RolesAllowed("USER")
	public String getUser() {
		return "Welcome user!";
	}

	@RequestMapping("/admin")
	@RolesAllowed("ADMIN")
	public String getAdmin() {
		return "Welcome admin!";
	}



}
