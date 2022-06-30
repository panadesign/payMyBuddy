package com.payMyBuddy.service;

import com.payMyBuddy.dao.AppUserRepository;
import com.payMyBuddy.model.AppUser;
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
class AppUserServiceTest {
	private AppUserService appUserService;

	@BeforeEach
	public void Init() {
		appUserService = new AppUserService(mockAppUserRepository);
	}

	@Mock
	private AppUserRepository mockAppUserRepository;

	@Test
	void getAllPersons() {
		//GIVEN
		List<AppUser> appUsers = new ArrayList<>();
		appUsers.add(new AppUser("email1", "prenom1", "nom1", "password1"));
		appUsers.add(new AppUser("email2", "prenom2", "nom2", "password2"));
		appUsers.add(new AppUser("email3", "prenom3", "nom3", "password3"));

		//WHEN
		when(mockAppUserRepository.findAll()).thenReturn(appUsers);
		List<AppUser> allAppUsers = appUserService.getAllPersons();

		//THEN
		Assertions.assertEquals(appUsers, allAppUsers);
	}

	@Test
	void addPerson() {
//		//GIVEN
//		AppUser appUser = new AppUser("j.doe@mail.com", "John", "Doe", "123");
//
//		//WHEN
//		when(mockAppUserRepository.save(appUser)).thenReturn(appUser);
//		AppUser appUserAdded = appUserService.addPerson(appUser);
//
//		//THEN
//		Assertions.assertEquals(appUser, appUserAdded);
	}

	@Test
	void getPersonByEmail() {
		//GIVEN
		AppUser appUser1 = new AppUser("email1", "prenom1", "nom1", "password1");

		//WHEN
		when(mockAppUserRepository.findByEmail("email1")).thenReturn(appUser1);
		AppUser appUserToFind = appUserService.getPersonByEmail("email1");

		//THEN
		Assertions.assertEquals(appUserToFind, appUser1);
	}

	@Test
	void addFriend() {
		//GIVEN
		List<AppUser> friendList = new ArrayList<>();
		AppUser appUser1 = new AppUser("email1", "prenom1", "nom1", "password1", friendList);
		AppUser appUser2 = new AppUser("email2", "prenom2", "nom2", "password2", friendList);

		//WHEN
		when(mockAppUserRepository.findByEmail(appUser2.getEmail())).thenReturn(appUser2);
		appUserService.addFriend(appUser2);

		//THEN
		Assertions.assertTrue(appUser1.getFriendsList().contains(appUser2));
	}
}