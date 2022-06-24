package com.payMyBuddy.service;

import com.payMyBuddy.dao.PersonRepository;
import com.payMyBuddy.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		Person person = personRepository.findByEmail(email);
		if (email == null) {
			throw new UsernameNotFoundException(email);
		}
		return new MyUserDetails(person);
	}
}
