package com.payMyBuddy.service.integration;

import com.payMyBuddy.dao.TransactionRepository;
import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.AccountStatus;
import com.payMyBuddy.model.Transaction;
import com.payMyBuddy.model.UserAccount;
import com.payMyBuddy.service.TransactionService;
import com.payMyBuddy.service.UserAccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.mockito.Mockito.when;


@SpringBootTest
@Sql("/test-data.sql")
class TransactionServiceIT {

	@Autowired
	TransactionService transactionService;

	@MockBean
	UserAccountService userAccountService;

	@MockBean
	TransactionRepository transactionRepository;

	@Autowired
	UserAccountRepository userAccountRepository;

	@Test
	@Transactional
	void shouldTransferMoneyToBank() {
		UserAccount userAccount = new UserAccount(UUID.randomUUID(), "mail@mail.com", "jeremy", "charpentier", "bla", "1234", AccountStatus.ACTIVE, new Account(UUID.randomUUID(), 1000));
		when(userAccountService.getPrincipalUser()).thenReturn(userAccount);

		transactionService.transferToBank(230, "tv");
		Assertions.assertEquals(770, userAccount.getAccount().getBalance());
	}

	@Test
	@Transactional
	void shouldAddMoneyToAccount() {
		UserAccount userAccount = new UserAccount(UUID.randomUUID(), "mail@mail.com", "jeremy", "charpentier", "bla", "1234", AccountStatus.ACTIVE, new Account(UUID.randomUUID(), 1000));
		when(userAccountService.getPrincipalUser()).thenReturn(userAccount);
		transactionService.addMoney(300.5);

		Assertions.assertEquals(1300.5, userAccount.getAccount().getBalance());

	}

	@Test
	@Transactional
	void shouldReturnAnExceptionAddingZero() {
		UserAccount userAccount = new UserAccount(UUID.randomUUID(), "mail@mail.com", "jeremy", "charpentier", "bla", "1234", AccountStatus.ACTIVE, new Account(UUID.randomUUID(), 1000));
		when(userAccountService.getPrincipalUser()).thenReturn(userAccount);

		Assertions.assertThrows(IllegalArgumentException.class, () -> transactionService.addMoney(0));
		Assertions.assertEquals(1000, userAccount.getAccount().getBalance());

	}

	@Test
	@Transactional
	void ShouldTransferMoneyToContact() {
		//GIVEN
		UserAccount userAccount = new UserAccount(UUID.randomUUID(), "mail@mail.com", "jeremy", "charpentier", "bla", "1234", AccountStatus.ACTIVE, new Account(UUID.randomUUID(), 1000));
		UserAccount contact = new UserAccount(UUID.randomUUID(), "contact@mail", "contact", "contact", "1", null, AccountStatus.ACTIVE, new Account(UUID.randomUUID(), 0));
		userAccount.getContactList().add(contact);
		Transaction transaction = new Transaction(contact.getId(), 200, "test", "EUR", userAccount, contact);

		//WHEN
		when(userAccountService.getPrincipalUser()).thenReturn(userAccount);
		when(transactionRepository.save(transaction)).thenReturn(transaction);

		transactionService.transferMoney(transaction.getId(), transaction.getAmount(), transaction.getDescription());

		//THEN
		Assertions.assertEquals(200, contact.getAccount().getBalance());
		Assertions.assertEquals(799, userAccount.getAccount().getBalance());
	}

}
