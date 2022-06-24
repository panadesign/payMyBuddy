package com.payMyBuddy.service;

import com.payMyBuddy.dao.PersonRepository;
import com.payMyBuddy.dto.PersonDto;
import com.payMyBuddy.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class PersonService {

	private final PersonRepository personRepository;

	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}

	public Person addPerson(PersonDto personDto) {

		Person person = new Person();
		person.setEmail(personDto.getEmail());
		person.setFirstname(personDto.getFirstname());
		person.setLastname(personDto.getLastname());
		person.setPassword(personDto.getPassword());
		return	personRepository.save(person);
	}

	public Person getPersonByEmail(String email) {
		return personRepository.findByEmail(email);
	}

	public void addFriend(Person person) {
		Person newFriend = getPersonByEmail(person.getEmail());
		person.getFriendsList().add(newFriend);
	}

}
