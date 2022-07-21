package com.payMyBuddy.service;

import com.payMyBuddy.dao.UserAccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

class ContactServiceImplTest {
	@Autowired
	private ContactService contactService;
	@Mock
	private PrincipalUser principalUserMock;

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
//		//GIVEN
//		UserAccount principalUser = new UserAccount("principalUser@mail.com", "prenom", "nom", "password");
//		UserAccount contact = new UserAccount("contact@mail.com", "prenom", "nom", "password");
//		List<UserAccount> contactList = principalUser.getContactList();
//		contactList.add(contact);
//
//		//WHEN
//		when(principalUserMock.getCurrentUserEmail()).thenReturn(principalUser.getEmail());
//		when(userAccountRepository.findByEmail(principalUser.getEmail())).thenReturn(Optional.of(contact));
//
//		List<UserAccount> listContact = contactService.getContactList();
//
//		//THEN
//		Assertions.assertTrue(listContact.contains(contact));
	}


//
//	@Test
//	void getContact() {
//	}
}