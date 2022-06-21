package com.payMyBuddy.service;

import com.payMyBuddy.dao.PersonRepository;
import com.payMyBuddy.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PersonServiceTest {
	private PersonService personService;

	@BeforeEach
	public void Init() {
		personService = new PersonService(mockPersonRepository);
	}

	@Mock
	private PersonRepository mockPersonRepository;

	@Test
	void getAllPersons() {
		//GIVEN
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("email1", "prenom1", "nom1", "password1"));
		persons.add(new Person("email2", "prenom2", "nom2", "password2"));
		persons.add(new Person("email3", "prenom3", "nom3", "password3"));

		//WHEN
		when(mockPersonRepository.findAll()).thenReturn(persons);
		List<Person> allPersons = personService.getAllPersons();

		//THEN
		Assertions.assertEquals(persons, allPersons);
	}

	@Test
	void addPerson() {
		//GIVEN
		Person person = new Person("j.doe@mail.com", "John", "Doe", "124");

		//WHEN
		when(mockPersonRepository.save(person)).thenReturn(person);
		Person personAdded = personService.addPerson(person);

		//THEN
		Assertions.assertEquals(person, personAdded);
	}

}