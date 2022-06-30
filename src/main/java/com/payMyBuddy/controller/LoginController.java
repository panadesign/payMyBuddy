package com.payMyBuddy.controller;

import com.payMyBuddy.dto.AppUserDto;
import com.payMyBuddy.model.AppUser;
import com.payMyBuddy.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	@Autowired
	AppUserService appUserService;

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
