package com.PayMyBuddy.config.accesData;

import com.PayMyBuddy.dao.PersonRepository;
import com.PayMyBuddy.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/person") // This means URL's start with /demo (after Application path)
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping(path="/allPersons")
	public @ResponseBody Iterable<Person> getAllPersons() {
		return personRepository.findAll();
	}

}
