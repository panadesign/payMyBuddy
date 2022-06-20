package com.payMyBuddy.service;

import com.payMyBuddy.dao.PersonRepository;
import com.payMyBuddy.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class PersonService {

	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Iterable<Person> getAllPersons() {
		return personRepository.findAll();
	}

	public Person addPerson(Person person) {
		personRepository.save(person);
		return person;
	}

}
