package com.payMyBuddy.service;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.AccountStatus;
import com.payMyBuddy.model.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {

	private ContactService contactService;

	@Mock
	private PrincipalUser mockPrincipalUser;
	@Mock
	private UserAccountService mockUserAccountService;
	@Mock
	private UserAccountRepository mockUserAccountRepository;

	//init
	@BeforeEach
	void init() {
		contactService = new ContactServiceImpl(
				mockUserAccountService,
				mockUserAccountRepository,
				mockPrincipalUser
		);
	}

	@Test
	void addContactByEmail() {
		//GIVEN
		UserAccount userConnected = new UserAccount(UUID.randomUUID(), "connected@mail.com", "firstname1", "lastname1", "123", "1234", AccountStatus.ACTIVE, new Account());
		UserAccount contact = new UserAccount(UUID.randomUUID(), "contact@mail.com", "firstname2", "lastname2", "123", "1234", AccountStatus.ACTIVE, new Account());

		//WHEN
		when(mockUserAccountRepository.findByEmail(mockPrincipalUser.getCurrentUserName())).thenReturn(Optional.of(userConnected));
		when(mockUserAccountRepository.findByEmail(contact.getEmail())).thenReturn(Optional.of(contact));

		contactService.addContactByEmail("contact@mail.com");

		//THEN
		Assertions.assertTrue(userConnected.getContactList().contains(contact));

	}

	@Test
	void addContactByEmailThrowExceptionCannotAddContactTwice() {
		//GIVEN
		List<UserAccount> contactList = new ArrayList<>();
		UserAccount userConnected = new UserAccount(UUID.randomUUID(), "connected@mail.com", "firstname1", "lastname1", "123", "1234", AccountStatus.ACTIVE, new Account(), contactList);
		UserAccount userToAdd = new UserAccount(UUID.randomUUID(), "contact@mail.com", "firstname1", "lastname1", "123", "1234", AccountStatus.ACTIVE, new Account());

		contactList.add(userToAdd);

		//WHEN
		when(mockUserAccountRepository.findByEmail(mockPrincipalUser.getCurrentUserName())).thenReturn(Optional.of(userConnected));
		when(mockUserAccountRepository.findByEmail(userToAdd.getEmail())).thenReturn(Optional.of(userToAdd));

		//THEN
		Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.addContactByEmail("contact@mail.com"));
	}

	@Test
	void addContactByEmailThrowExceptionPrincipalUserCannotBeContact() {
		//GIVEN
		UserAccount userConnected = new UserAccount(UUID.randomUUID(), "connected@mail.com", "firstname1", "lastname1", "1234", "123", AccountStatus.ACTIVE, new Account());
		UserAccount userToAdd = new UserAccount(UUID.randomUUID(), "connected@mail.com", "firstname1", "lastname1", "123", "1234", AccountStatus.ACTIVE, new Account());

		//WHEN
		when(mockUserAccountRepository.findByEmail(mockPrincipalUser.getCurrentUserName())).thenReturn(Optional.of(userConnected));
		when(mockUserAccountRepository.findByEmail(userToAdd.getEmail())).thenReturn(Optional.of(userToAdd));

		//THEN
		Assertions.assertThrows(IllegalArgumentException.class, () -> contactService.addContactByEmail("connected@mail.com"));
	}

	@Test
	void removeContactByEmail() {
		//GIVEN
		List<UserAccount> contactList = new ArrayList<>();
		UserAccount userConnected = new UserAccount(UUID.randomUUID(), "connected@mail.com", "firstname1", "lastname1", "123", "1234", AccountStatus.ACTIVE, new Account(), contactList);
		UserAccount contact = new UserAccount(UUID.randomUUID(), "contact@mail.com", "firstname2", "lastname2", "123", "1234", AccountStatus.ACTIVE, new Account());

		contactList.add(contact);

		//WHEN
		when(mockUserAccountRepository.findByEmail(mockPrincipalUser.getCurrentUserName())).thenReturn(Optional.of(userConnected));
		when(mockUserAccountRepository.save(mockUserAccountService.getPrincipalUser())).thenReturn(userConnected);

		contactService.removeContactByEmail("contact@mail.com");

		//THEN
		Assertions.assertTrue(userConnected.getContactList().isEmpty());

	}

	@Test
	void getAllContacts() {
		//GIVEN
		List<UserAccount> contactList = new ArrayList<>();
		UserAccount userConnected = new UserAccount(UUID.randomUUID(), "connected@mail.com", "firstname1", "lastname1", "123", "1234", AccountStatus.ACTIVE, new Account(), contactList);

		UserAccount contact1 = new UserAccount(UUID.randomUUID(), "contact1@mail.com", "firstname1", "lastname1", "123", "1234", AccountStatus.ACTIVE, new Account());
		UserAccount contact2 = new UserAccount(UUID.randomUUID(), "contact2@mail.com", "firstname2", "lastname2", "123", "1234", AccountStatus.ACTIVE, new Account());

		contactList.add(contact1);
		contactList.add(contact2);

		//WHEN
		when(mockUserAccountRepository.findByEmail(mockPrincipalUser.getCurrentUserName())).thenReturn(Optional.of(userConnected));

		List<UserAccount> contacts = contactService.getContactList();

		//THEN
		Assertions.assertEquals(contacts.size(), 2);

	}

}