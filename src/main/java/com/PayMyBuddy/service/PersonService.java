package com.PayMyBuddy.service;

import com.PayMyBuddy.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepository;

	public List getAllPersons() {
		List persons = new ArrayList();
		personRepository.findAll().forEach(person -> persons.add(person));
		return persons;
	}

}
