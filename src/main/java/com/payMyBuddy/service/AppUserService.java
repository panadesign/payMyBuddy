package com.payMyBuddy.service;

import com.payMyBuddy.dao.AppUserRepository;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.model.AppUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class AppUserService {

	private final AppUserRepository appUserRepository;

	public AppUserService(AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
	}

	public List<AppUser> getAllPersons() {
		return appUserRepository.findAll();
	}

	public AppUser addAppUser(AppUser appUser) {
		appUser.setEmail(appUser.getEmail());
		appUser.setFirstname(appUser.getFirstname());
		appUser.setLastname(appUser.getLastname());
		appUser.setPassword(appUser.getPassword());
		appUserRepository.save(appUser);
		return appUser;
	}

	public AppUser getPersonByEmail(String email) {
		return appUserRepository.findByEmail(email);
	}

	public void addFriend(AppUser appUser) {
		AppUser newFriend = getPersonByEmail(appUser.getEmail());
		appUser.getFriendsList().add(newFriend);
	}


	public ProfileDto getProfileByEmail(String email) {
		AppUser appUser = appUserRepository.findByEmail(email);
		return new ProfileDto(appUser.getEmail(), appUser.getFirstname(), appUser.getLastname());
	}

}
