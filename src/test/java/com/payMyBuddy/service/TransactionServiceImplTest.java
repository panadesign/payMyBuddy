package com.payMyBuddy.service;

import com.payMyBuddy.dao.TransactionRepository;
import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.exception.DebtorAccountException;
import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.AccountStatus;
import com.payMyBuddy.model.Transaction;
import com.payMyBuddy.model.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

	@InjectMocks
	TransactionService transactionService = new TransactionServiceImpl();

	@Mock
	private UserAccountService mockUserAccountService;
	@Mock
	private UserAccountRepository mockUserAccountRepository;
	@Mock
	private TransactionRepository mockTransactionRepository;
	@Mock
	private PrincipalUser mockPrincipalUser;

	@Test
	void transferMoney() {
		//GIVEN
		Account debtorAccount = new Account(UUID.randomUUID(), 700);
		Account creditorAccount = new Account(UUID.randomUUID(), 30);

		UserAccount debtor = new UserAccount(UUID.randomUUID(), "connected@mail.com", "firstname1", "lastname1", "123", AccountStatus.ACTIVE, debtorAccount);
		UserAccount creditor = new UserAccount(UUID.randomUUID(), "creditor@mail.com", "firstname1", "lastname1", "123", AccountStatus.ACTIVE, creditorAccount);
		debtor.getContactList().add(creditor);

		Transaction transaction1 = new Transaction(creditor.getId(), 137, "test", "EUR", debtor, creditor);

		//WHEN
		when(mockUserAccountService.getPrincipalUser()).thenReturn(debtor);
		when(mockUserAccountRepository.save(debtor)).thenReturn(debtor);
		when(mockUserAccountRepository.save(creditor)).thenReturn(creditor);
		when(mockTransactionRepository.save(transaction1)).thenReturn(transaction1);

		transactionService.transferMoney(transaction1.getId(), transaction1.getAmount(),transaction1.getDescription());

		//THEN
		Assertions.assertTrue(creditor.getAccount().getBalance() == 167);
	}

	@Test
	void transferMoneyWithOuEnoughMoney() {
		//GIVEN
		Account debtorAccount = new Account(UUID.randomUUID(), 70);
		Account creditorAccount = new Account(UUID.randomUUID(), 30);

		UserAccount debtor = new UserAccount(UUID.randomUUID(), "connected@mail.com", "firstname1", "lastname1", "123", AccountStatus.ACTIVE, debtorAccount);
		UserAccount creditor = new UserAccount(UUID.randomUUID(), "creditor@mail.com", "firstname1", "lastname1", "123", AccountStatus.ACTIVE, creditorAccount);
		debtor.getContactList().add(creditor);

		Transaction transaction1 = new Transaction(creditor.getId(), 137, "test", "EUR", debtor, creditor);

		//WHEN
		when(mockUserAccountService.getPrincipalUser()).thenReturn(debtor);

		//THEN
		Assertions.assertThrows(DebtorAccountException.class, ()-> transactionService.transferMoney(transaction1.getId(), transaction1.getAmount(),transaction1.getDescription()));
	}

	@Test
	void getAllTransactions() {
		//GIVEN
		List<Transaction> transactions = new ArrayList<>();

		UserAccount debtor = new UserAccount(UUID.randomUUID(), "connected@mail.com", "firstname1", "lastname1", "123", AccountStatus.ACTIVE, new Account());
		UserAccount creditor = new UserAccount(UUID.randomUUID(), "creditor@mail.com", "firstname1", "lastname1", "123", AccountStatus.ACTIVE, new Account());
		Transaction transaction1 = new Transaction(debtor, creditor, 137, "euro", "test");
		transactions.add(transaction1);

		//WHEN
		when(mockUserAccountService.getPrincipalUser()).thenReturn(debtor);
		when(mockTransactionRepository.findAllByCreditorIdOrDebtorIdOrderByCreationDate(debtor.getId(), debtor.getId())).thenReturn(transactions);

		List<Transaction> allTransactions = transactionService.getAllTransactions();

 		//THEN
		Assertions.assertTrue(allTransactions.contains(transaction1));
	}
}