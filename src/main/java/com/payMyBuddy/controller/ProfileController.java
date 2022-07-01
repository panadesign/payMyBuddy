package com.payMyBuddy.controller;

import com.payMyBuddy.dto.FriendDto;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.dto.UserAccountDto;
import com.payMyBuddy.model.UserAccount;
import com.payMyBuddy.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProfileController {

	@Autowired
	UserAccountService userAccountService;

	@GetMapping("/profile")
	private String userAccount(Model model, String email) {
		email = userAccountService.getCurrentUserMail();
		ProfileDto profileDto = userAccountService.getPersonByEmail(email);
		model.addAttribute("profileDto", profileDto);
		return "/profile";
	}

//	@GetMapping("/profile")
//	private String userAccount(Model model, String email) {
//		FriendDto friendDto = userAccountService.getPersonByEmail(email);
//		model.addAttribute("friendDto", friendDto);
//		return "/profile";
//	}

}
