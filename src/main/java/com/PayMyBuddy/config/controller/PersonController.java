package com.PayMyBuddy.config.controller;

import com.PayMyBuddy.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping("/persons")
	private List getAllPersons() {
		return personService.getAllPersons();
	}

}
