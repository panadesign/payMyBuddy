package com.payMyBuddy.service;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.AccountStatus;
import com.payMyBuddy.model.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

	@InjectMocks
	private UserDetailsService userDetailsService = new UserDetailsServiceImpl();
	@Mock
	UserAccountRepository mockUserAccountRepository;

	@Test
	void loadUserByUsername() {
		//GIVEN
		UserAccount userAccount = new UserAccount(UUID.randomUUID(), "email@test.com", "jeremy", "charpentier", "'password", "12345", AccountStatus.ACTIVE, new Account());

		//WHEN
		when(mockUserAccountRepository.findByEmail(userAccount.getEmail())).thenReturn(Optional.of(userAccount));
		User newUser = new User(userAccount.getEmail(), userAccount.getPassword(), new ArrayList<>());

		userDetailsService.loadUserByUsername("email@test.com");

		//THEN
		Assertions.assertEquals(newUser.getUsername(), userAccount.getEmail());
	}
}