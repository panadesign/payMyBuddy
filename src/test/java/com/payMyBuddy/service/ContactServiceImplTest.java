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
	}

	@Test
	void removeContactByEmail() {
	}

	@Test
	void getContactList() {
	}


//
//	@Test
//	void getContact() {
//	}
}