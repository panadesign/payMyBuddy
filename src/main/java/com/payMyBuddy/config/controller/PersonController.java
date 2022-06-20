package com.payMyBuddy.config.controller;

import com.payMyBuddy.model.Person;
import com.payMyBuddy.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	@GetMapping("/person")
	private Iterable<Person> getAllPersons() {
		return personService.getAllPersons();
	}

//	@PostMapping("/person")
//	private void addPerson() {
//		personService.addPerson();
//	}

}
