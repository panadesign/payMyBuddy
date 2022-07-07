package com.payMyBuddy.controller;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.model.UserAccount;
import com.payMyBuddy.service.MapperService;
import com.payMyBuddy.service.PrincipalUser;
import com.payMyBuddy.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private PrincipalUser principalUser;
	@Autowired
	private MapperService mapperService;

	@GetMapping("/profile")
	private String userAccount(Model model) {
		String email = principalUser.getCurrentUserEmail();
		ProfileDto profileDto = userAccountService.getUserAccountByEmail(email);
		model.addAttribute("profileDto", profileDto);
		return "/profile";
	}

	@GetMapping("/profile/contacts")
	public String getContact(Model model) {
		Optional<UserAccount> userPrincipal = userAccountRepository.findByEmail(principalUser.getCurrentUserEmail());
		List<UserAccount> userAccountList = userPrincipal.get().getContactList();
		mapperService.convertUserAccountListToContactDtoList(userAccountList);
		model.addAttribute("userAccountList", userAccountList);

		return "/profile";
	}

}
