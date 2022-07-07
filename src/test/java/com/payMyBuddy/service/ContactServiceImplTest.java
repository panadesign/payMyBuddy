package com.payMyBuddy.service;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.model.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.Mockito.when;

class ContactServiceImplTest {
	@Autowired
	private ContactService contactService;
	@Autowired
	private PrincipalUser principalUser;

	@Mock
	UserAccountRepository userAccountRepository;

	@Test
	void addContactByEmail() {
		UserAccount userToAdd = new UserAccount("j.doe@mail.com","John","Doe", "2");
		when(principalUser.getCurrentUserEmail()).thenReturn("j.doe@mail.com");
		when(userAccountRepository.findByEmail("j.doe@mail.com")).thenReturn(Optional.of(userToAdd));

		Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.addContactByEmail("j.doe@mail.com"));
	}

	@Test
	void getContact() {
	}
}