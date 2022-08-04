package com.payMyBuddy.service.integration;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.AccountStatus;
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
class transactionServiceIT {

	@Autowired
	TransactionService transactionService;

	@Autowired
	UserAccountRepository userAccountRepository;

	@MockBean
	UserAccountService userAccountService;

	@Test
	@Transactional
	void shouldCreateTransactionToBank() {
		UserAccount userAccount = new UserAccount(UUID.randomUUID(), "mail@mail.com", "jeremy", "charpentier", "bla", "1234", AccountStatus.ACTIVE, new Account(UUID.randomUUID(), 1000));
		when(userAccountService.getPrincipalUser()).thenReturn(userAccount);

		transactionService.transferToBank(230, "tv");
		Assertions.assertEquals(770, userAccount.getAccount().getBalance());
	}

}