package com.PayMyBuddy;

import com.PayMyBuddy.model.Person;
import com.PayMyBuddy.service.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log4j2
public class PayMyBuddyApplication {

//	@Autowired
//	private PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(PayMyBuddyApplication.class, args);
	}

//	@Override
//	public void run(String... args) {
//		Iterable<Person> persons = personService.getPersons();
//		persons.forEach(person -> System.out.println(person.getEmail()));
//	}

}
