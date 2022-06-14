package com.PayMyBuddy;

import com.PayMyBuddy.model.Person;
import com.PayMyBuddy.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PayMyBuddyApplication implements CommandLineRunner {

	@Autowired
	private PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(PayMyBuddyApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Iterable<Person> persons = personService.getPersons();
		persons.forEach(person -> System.out.println(person.getEmail()));

		Long countPersons = personService.countPersons();
		System.out.println(countPersons);
	}

}
