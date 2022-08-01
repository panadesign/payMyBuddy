package com.payMyBuddy.controller;

import com.payMyBuddy.model.UserAccount;
import com.payMyBuddy.service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

	private final UserAccountService userAccountService;

	SignUpController(UserAccountService userAccountService) {
		this.userAccountService = userAccountService;
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("appUser", new UserAccount());
		return "signup";
	}

	@PostMapping("/signup")
	public String addUserAccount(@ModelAttribute UserAccount userAccount) {
		userAccountService.registerNewUserAccount(userAccount);
		return "redirect:/login";
	}
}
