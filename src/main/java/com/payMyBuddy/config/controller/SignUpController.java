package com.payMyBuddy.config.controller;

import com.payMyBuddy.dto.AppUserDto;
import com.payMyBuddy.model.AppUser;
import com.payMyBuddy.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class SignUpController {

	@Autowired
	private AppUserService appUserService;

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("appUser", new AppUser());
		return "signup";
	}

	@PostMapping("/addAppUser")
	@ResponseBody
	public String addAppUser(@ModelAttribute AppUserDto appUserDto) throws Exception {
		appUserService.registerNewUserAccount(appUserDto);
		return"Your account has been created !";
	}
}
