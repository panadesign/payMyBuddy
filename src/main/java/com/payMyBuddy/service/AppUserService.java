package com.payMyBuddy.service;

import com.payMyBuddy.dao.AccountRepository;
import com.payMyBuddy.dao.AppUserRepository;
import com.payMyBuddy.dto.AppUserDto;
import com.payMyBuddy.exception.RessourceNotFoundException;
import com.payMyBuddy.exception.UserAlreadyExistException;
import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Component
public class AppUserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	private final AppUserRepository appUserRepository;

	public AppUserService(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	public List<AppUser> getAllPersons() {
		return appUserRepository.findAll();
	}


	public AppUser getPersonByEmail(String email) {
		
		return appUserRepository.findByEmail(email)
				.orElseThrow(() -> new RessourceNotFoundException("User not found with email : " + email));
	}

	public void addFriend(AppUser appUser) {
		AppUser newFriend = getPersonByEmail(appUser.getEmail());
		appUser.getFriendsList().add(newFriend);
	}

	private boolean emailExist(String email) {
		return appUserRepository.findByEmail(email).isPresent();
	}

	public AppUser registerNewUserAccount(AppUserDto appUserDto) {
		if (emailExist(appUserDto.getEmail())) {
			throw new UserAlreadyExistException("There is an account with that email address:" + appUserDto.getEmail());
		}
		String password = passwordEncoder.encode(appUserDto.getPassword());
		AppUser appUser = new AppUser(appUserDto.getEmail(), appUserDto.getFirstname(), appUserDto.getLastname(), password);

		return appUserRepository.save(appUser);
	}



}
