package com.payMyBuddy.controller;

import com.payMyBuddy.dto.ContactInputDto;
import com.payMyBuddy.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConnectionController {
	@Autowired
	private ContactService contactService;

	@PostMapping("/connection")
	public String addContact(Model model, String email) {
		ContactInputDto contactInputDto = contactService.addContactByEmail(email);
		model.addAttribute("contactInputDto", contactInputDto);
		return "transaction";
	}

//	DELETE CONTACT
}
