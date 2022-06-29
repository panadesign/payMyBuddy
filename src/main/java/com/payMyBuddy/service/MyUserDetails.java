package com.payMyBuddy.service;

import com.payMyBuddy.dao.AppUserRepository;
import com.payMyBuddy.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class MyUserDetails {

	private UUID id;
	private String email;
	private String firstname;
	private String lastname;
	private String password;

	private AppUser appUser;

	@Autowired
	AppUserRepository appUserRepository;

	public MyUserDetails(AppUser appUser) {
		this.appUser = appUser;
	}

//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return null;
//	}
//
//	@Override
//	public String getPassword() {
//		person = personRepository.findByEmail();
//	}
//
//	@Override
//	public String getUsername() {
//		return null;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
}
