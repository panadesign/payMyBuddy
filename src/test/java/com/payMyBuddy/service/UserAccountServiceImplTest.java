package com.payMyBuddy.service;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.model.UserAccount;
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
class UserAccountServiceImplTest {

	private UserAccountService userAccountService;

	@Mock
	MapperService mapperService;

	@BeforeEach
	public void Init() {
		userAccountService = new UserAccountServiceImpl(mockUserAccountRepository);
	}

	@Mock
	private UserAccountRepository mockUserAccountRepository;

	@Test
	void getAllUsersAccount() {
		//GIVEN
		List<UserAccount> appUsers = new ArrayList<>();
		appUsers.add(new UserAccount("email1", "prenom1", "nom1", "password1"));
		appUsers.add(new UserAccount("email2", "prenom2", "nom2", "password2"));
		appUsers.add(new UserAccount("email3", "prenom3", "nom3", "password3"));

		//WHEN
		when(mockUserAccountRepository.findAll()).thenReturn(appUsers);
		List<UserAccount> allAppUsers = userAccountService.getAllUsersAccount();

		//THEN
		Assertions.assertEquals(appUsers, allAppUsers);
	}

//	@Test
//	void getUserAccountByEmail() {
//		//GIVEN
//		UserAccount userAccount = new UserAccount("email1@test.com", "prenom1", "nom1", "password1");
//
//		ProfileDto profileDto = new ProfileDto("email1@test.com", "prenom1", "nom1");
//		when(mapperService.convertUserAccountToProfileDto(userAccount)).thenReturn(profileDto);
//
//		ProfileDto userAccountToFind = userAccountService.getUserAccountByEmail("email1@test.com");
//		Assertions.assertTrue(userAccountToFind.equals(profileDto));
//	}

	@Test
	void testGetAllUsersAccount() {
	}

	@Test
	void testGetUserAccountByEmail() {
	}

	@Test
	void getUserAccountById() {
	}

	@Test
	void registerNewUserAccount() {
	}

	@Test
	void getPrincipalUser() {
	}
}