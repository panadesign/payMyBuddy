package com.payMyBuddy.controller;

import com.payMyBuddy.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConnectionController {
	@Autowired
	private ContactService contactService;

	@PostMapping("/addConnection")
	public String addContact(String email) {
		contactService.addContactByEmail(email);
		return "redirect:/transaction";
	}


	@GetMapping("/deleteConnection")
	public String deleteContact(@RequestParam String email) {
		contactService.removeContactByEmail(email);
		return "redirect:/profile";
	}
}
