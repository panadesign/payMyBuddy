package com.payMyBuddy.controller;

import com.payMyBuddy.dto.ContactInputDto;
import com.payMyBuddy.dto.TransactionDto;
import com.payMyBuddy.model.Transaction;
import com.payMyBuddy.model.UserAccount;
import com.payMyBuddy.service.ContactService;
import com.payMyBuddy.service.TransactionService;
import com.payMyBuddy.service.UserAccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@Log4j2
public class TransactionController {

	@Autowired
	private ContactService contactService;

	@Autowired
	UserAccountService userAccountService;
	@Autowired
	private TransactionService transactionService;

	@PostMapping("/transaction")
	public String transaction(Model model, @RequestParam("id") UUID id, @RequestParam("amount") float amount, @RequestParam("description") String description) {
		log.debug("Transfer money to selected contact with : ID = " + id
				+ " / amount = " + amount
				+ " / description = " + description
		);

		Transaction transaction =  transactionService.transferMoney(id, amount, description);
		model.addAttribute("transaction", transaction);
		model.addAttribute("transactionSuccess", "Your transaction is successfully added");
		
		log.debug("Transfer done");
		return "redirect:/transaction";
	}

	@PostMapping("/bankTransaction")
	public String transaction(Model model, @RequestParam("iban") String iban, @RequestParam("amount") float amount, @RequestParam("description") String description) {
		log.debug("Transfer money to selected contact with : ID = " + iban
				+ " / amount = " + amount
				+ " / description = " + description
		);

		Transaction transaction =  transactionService.transferToBank(iban, amount, description);
		model.addAttribute("transaction", transaction);
		model.addAttribute("transactionSuccess", "Your transaction is successfully added");

		log.debug("Transfer done");
		return "redirect:/transaction";
	}

	@GetMapping("/transaction")
	public String transfer(Model model) {
		List<UserAccount> userAccountContactList = contactService.getContactList();
		List<ContactInputDto> contactList = userAccountContactList
				.stream()
				.map(ContactInputDto::new)
				.collect(Collectors.toList());

		model.addAttribute("contactList", contactList);

		log.debug("Display all transactions");
		List<Transaction> transactionList = transactionService.getAllTransactions();
		List<TransactionDto> transactions =	transactionList
				.stream()
				.map(TransactionDto::new)
				.collect(Collectors.toList());

		model.addAttribute("transactions", transactions);

		UserAccount connectedUser = userAccountService.getPrincipalUser();
		String iban = connectedUser.getIban();
		model.addAttribute("iban", iban);

		return "/transaction";
	}
}
