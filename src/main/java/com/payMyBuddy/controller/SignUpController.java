package com.payMyBuddy.controller;

import com.payMyBuddy.dto.UserAccountDto;
import com.payMyBuddy.model.UserAccount;
import com.payMyBuddy.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

	@Autowired
	private UserAccountService userAccountService;

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("appUser", new UserAccount());
		return "signup";
	}

	@PostMapping("/signup")
	public String addUserAccount(@ModelAttribute UserAccountDto userAccountDto) throws Exception {
		userAccountService.registerNewUserAccount(userAccountDto);
		return "login";
	}
}
