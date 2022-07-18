package com.payMyBuddy.controller;

import com.payMyBuddy.dto.ContactInputDto;
import com.payMyBuddy.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConnectionController {
	@Autowired
	private ContactService contactService;

	@PostMapping("/addConnection")
	public String addContact(Model model, String email) {
		ContactInputDto contactInputDto = contactService.addContactByEmail(email);
		model.addAttribute("contactInputDto", contactInputDto);
		return "redirect:/transaction";
	}


	@GetMapping ("/deleteConnection")
	public String deleteContact(Model model, @RequestParam String email) {
		Boolean contactInputDto = contactService.removeContactByEmail(email);
		model.addAttribute("contactInputDto", contactInputDto);
		return "redirect:/profile";
	}
}
