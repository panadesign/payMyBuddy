package com.payMyBuddy.service;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.dto.UserAccountDto;
import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.AccountStatus;
import com.payMyBuddy.model.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AppUserServiceTest {
	private UserAccountService userAccountService;

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



}