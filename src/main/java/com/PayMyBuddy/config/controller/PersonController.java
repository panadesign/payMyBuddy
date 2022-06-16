package com.PayMyBuddy.config.controller;

import com.PayMyBuddy.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping("/person")
	private List getAllPersons() {
		return personService.getAllPersons();
	}

//	@PostMapping("/person")
//	private void addPerson() {
//		personService.addPerson();
//	}

}
