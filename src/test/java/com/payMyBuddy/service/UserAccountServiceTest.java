//package com.payMyBuddy.service;
//
//import com.payMyBuddy.dao.UserAccountRepository;
//import com.payMyBuddy.model.UserAccount;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//class AppUserServiceTest {
//	private UserAccountService appUserService;
//
//	@BeforeEach
//	public void Init() {
//		appUserService = new UserAccountService(mockAppUserRepository);
//	}
//
//	@Mock
//	private UserAccountRepository mockAppUserRepository;
//
//	@Test
//	void getAllPersons() {
//		//GIVEN
//		List<UserAccount> appUsers = new ArrayList<>();
//		appUsers.add(new UserAccount("email1", "prenom1", "nom1", "password1"));
//		appUsers.add(new UserAccount("email2", "prenom2", "nom2", "password2"));
//		appUsers.add(new UserAccount("email3", "prenom3", "nom3", "password3"));
//
//		//WHEN
//		when(mockAppUserRepository.findAll()).thenReturn(appUsers);
//		List<UserAccount> allAppUsers = appUserService.getAllPersons();
//
//		//THEN
//		Assertions.assertEquals(appUsers, allAppUsers);
//	}
//
//	@Test
//	void addPerson() {
////		//GIVEN
////		UserAccount appUser = new UserAccount("j.doe@mail.com", "John", "Doe", "123");
////
////		//WHEN
////		when(mockAppUserRepository.save(appUser)).thenReturn(appUser);
////		UserAccount appUserAdded = appUserService.addPerson(appUser);
////
////		//THEN
////		Assertions.assertEquals(appUser, appUserAdded);
//	}
//
//	@Test
//	void getPersonByEmail() {
//		//GIVEN
//		UserAccount appUser1 = new UserAccount("email1", "prenom1", "nom1", "password1");
//
//		//WHEN
//		when(mockAppUserRepository.findByEmail("email1")).thenReturn(appUser1);
//		UserAccount appUserToFind = appUserService.getPersonByEmail("email1");
//
//		//THEN
//		Assertions.assertEquals(appUserToFind, appUser1);
//	}
//
//	@Test
//	void addFriend() {
//		//GIVEN
//		List<UserAccount> friendList = new ArrayList<>();
//		UserAccount appUser1 = new UserAccount("email1", "prenom1", "nom1", "password1", friendList);
//		UserAccount appUser2 = new UserAccount("email2", "prenom2", "nom2", "password2", friendList);
//
//		//WHEN
//		when(mockAppUserRepository.findByEmail(appUser2.getEmail())).thenReturn(appUser2);
//		appUserService.addFriend(appUser2);
//
//		//THEN
//		Assertions.assertTrue(appUser1.getFriendsList().contains(appUser2));
//	}
//}