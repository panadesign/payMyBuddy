package com.payMyBuddy.service;

import com.payMyBuddy.dao.AppUserRepository;
import com.payMyBuddy.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		AppUser appUser = appUserRepository.findByEmail(email);
		if(email == null) {
			throw new UsernameNotFoundException("email");
		}

		Collection authorities = new ArrayList<>();

		User user = new User(appUser.getEmail(), appUser.getPassword(), authorities);
		return user;
	}

}
