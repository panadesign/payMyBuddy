package com.PayMyBuddy.service;

import com.PayMyBuddy.dao.PersonRepository;
import com.PayMyBuddy.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	@Autowired
	PersonRepository personRepository;

	public Iterable<Person> getPersons() {
		return personRepository.findAll();
	}

	public Long countPersons() {
		return personRepository.count();
	}
}
