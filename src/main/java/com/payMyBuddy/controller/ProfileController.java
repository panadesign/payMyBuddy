package com.payMyBuddy.controller;

import com.payMyBuddy.dto.ContactInputDto;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.model.UserAccount;
import com.payMyBuddy.service.ContactService;
import com.payMyBuddy.service.PrincipalUser;
import com.payMyBuddy.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ProfileController {
	@Autowired
	private ContactService contactService;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private PrincipalUser principalUser;

	@GetMapping("/profile")
	private String userAccount(Model model) {
		String email = principalUser.getCurrentUserEmail();
		ProfileDto profileDto = userAccountService.getUserAccountByEmail(email);
		model.addAttribute("profileDto", profileDto);
		
		List<UserAccount> userAccountContactList = contactService.getContactList();
		List<ContactInputDto> contactList = userAccountContactList
				.stream()
				.map(ContactInputDto::new)
				.collect(Collectors.toList());
		model.addAttribute("contactList", contactList);
		
		return "/profile";
	}

}
