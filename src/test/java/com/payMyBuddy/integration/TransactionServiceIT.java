package com.payMyBuddy.integration;

import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.AccountStatus;
import com.payMyBuddy.model.Transaction;
import com.payMyBuddy.model.UserAccount;
import com.payMyBuddy.service.TransactionService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class TransactionServiceIT {

	@Autowired
	private TransactionService transactionService;

	@Before
	public void init() {
		User user = new User("debtor@mail.com", "deb", List.of(new SimpleGrantedAuthority("USER")));
	}

	@Test
	void shouldCreateATransaction() {
		//GIVEN
		List<UserAccount> contacts = new ArrayList<>();
		UserAccount debtor = new UserAccount(UUID.randomUUID(), "debtor@mail.com", "deb", "deb", "password", AccountStatus.ACTIVE, new Account(UUID.randomUUID(), 200), contacts);
		UserAccount creditor = new UserAccount(UUID.randomUUID(), "creditor@mail.com", "cred", "cred", "password", AccountStatus.ACTIVE, new Account(UUID.randomUUID(), 50), new ArrayList<>());
		contacts.add(creditor);


		Transaction transaction = new Transaction(creditor.getId(), 130, "firstTransaction");



		transactionService.transferMoney(transaction.getId(), transaction.getAmount(), transaction.getDescription());

	}

}
