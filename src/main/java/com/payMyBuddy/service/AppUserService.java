package com.payMyBuddy.service;

import com.payMyBuddy.dao.AppUserRepository;
import com.payMyBuddy.dto.AppUserDto;
import com.payMyBuddy.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

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

//	public AppUser addAppUser(AppUser appUser) {
//		appUser.setEmail(appUser.getEmail());
//		appUser.setFirstname(appUser.getFirstname());
//		appUser.setLastname(appUser.getLastname());
//		appUser.setPassword(appUser.getPassword());
//		appUserRepository.save(appUser);
//		return appUser;
//	}

	public AppUser getPersonByEmail(String email) {
		return appUserRepository.findByEmail(email);
	}

	public void addFriend(AppUser appUser) {
		AppUser newFriend = getPersonByEmail(appUser.getEmail());
		appUser.getFriendsList().add(newFriend);
	}

	public AppUser registerNewUserAccount(AppUserDto appUserDto) throws Exception {
//		if (emailExist(appUserDto.getEmail())) {
//			throw new Exception(
//					"There is an account with that email address:" + appUserDto.getEmail());
//		}
		AppUser appUser = new AppUser();
		appUser.setFirstname(appUserDto.getFirstname());
		appUser.setLastname(appUserDto.getLastname());

		appUser.setPassword(passwordEncoder.encode(appUserDto.getPassword()));

		appUser.setEmail(appUserDto.getEmail());
		return appUserRepository.save(appUser);
	}

//	private boolean emailExist(String email) {
//		appUserRepository.findByEmail(appUserRepository.get);
//
//	}

}
