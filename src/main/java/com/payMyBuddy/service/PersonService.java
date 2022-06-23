package com.payMyBuddy.service;

import com.payMyBuddy.dao.PersonRepository;
import com.payMyBuddy.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class PersonService {

	private final PersonRepository personRepository;
	private Person person;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}

	public Person addPerson(Person person) {
		personRepository.save(person);
		return person;
	}

	public Person getPersonByEmail(String email) {
		return personRepository.findByEmail(email);
	}

	public void addFriend(Person person) {
		Person newFriend = getPersonByEmail(person.getEmail());
		person.getFriendList().add(newFriend);
	}

}
