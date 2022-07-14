package com.payMyBuddy.controller;

import com.payMyBuddy.dto.ContactDto;
import com.payMyBuddy.dto.ContactInputDto;
import com.payMyBuddy.dto.ContactOutputDto;
import com.payMyBuddy.model.UserAccount;
import com.payMyBuddy.service.ContactService;
import com.payMyBuddy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TransactionController {

	@Autowired
	private ContactService contactService;
	@Autowired
	private TransactionService transactionService;

	@PostMapping("/transaction")
	public String transaction(Model model, String email, float amount, String description) {

		//CREATE A TRANSACTION
		model.addAttribute("contactList", new ContactInputDto());
		transactionService.transferMoney(email, amount, description);

		return "transaction";
	}

	@GetMapping("/transaction")
	public String transfer(Model model) {
		List<UserAccount> userAccountContactList = contactService.getContactList();
		List<ContactInputDto> contactList = userAccountContactList
				.stream()
				.map(ContactInputDto::new)
				.collect(Collectors.toList());

		model.addAttribute("contactList", contactList);

		//TODO recuperate transactions


		return "/transaction";
	}



}
