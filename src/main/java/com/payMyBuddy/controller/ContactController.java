package com.payMyBuddy.controller;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.dto.ContactDto;
import com.payMyBuddy.dto.ContactOutputDto;
import com.payMyBuddy.model.UserAccount;
import com.payMyBuddy.service.ContactService;
import com.payMyBuddy.service.MapperService;
import com.payMyBuddy.service.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ContactController {
	@Autowired
	private ContactService contactService;

	@Autowired
	private PrincipalUser principalUser;

	@Autowired
	private MapperService mapperService;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@PostMapping("/contact")
	public String addContact(Model model, String email) {
		ContactDto contactDto = contactService.addContactByEmail(email);
		model.addAttribute("contactDto", contactDto);
		return "/transfer";
	}

	@GetMapping("/contact")
	public String getContact(Model model) {
		List<UserAccount> userAccountContactList = contactService.getContactList();
		
		var contactList = userAccountContactList
				.stream()
				.map(ContactOutputDto::new);
		
		model.addAttribute("contactList", contactList);

		return "/transfer";
	}
//	DELETE CONTACT
}
