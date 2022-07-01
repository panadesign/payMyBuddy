package com.payMyBuddy.controller;

import com.payMyBuddy.model.UserAccount;
import com.payMyBuddy.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserAccountController {

	@Autowired
	private UserAccountService userAccountService;

	@GetMapping("/persons")
	private List<UserAccount> getAllPersons() {
		return userAccountService.getAllPersons();
	}

}
