package com.payMyBuddy.config.controller;

import com.payMyBuddy.model.AppUser;
import com.payMyBuddy.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AppUserController {

	@Autowired
	private AppUserService appUserService;

	@GetMapping("/persons")
	private List<AppUser> getAllPersons() {
		return appUserService.getAllPersons();
	}



}
