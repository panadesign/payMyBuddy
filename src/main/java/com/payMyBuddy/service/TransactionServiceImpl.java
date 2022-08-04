package com.payMyBuddy.service;

import com.payMyBuddy.constants.Commission;
import com.payMyBuddy.dao.TransactionRepository;
import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.exception.DebtorAccountException;
import com.payMyBuddy.exception.ResourceNotFoundException;
import com.payMyBuddy.model.Transaction;
import com.payMyBuddy.model.UserAccount;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class TransactionServiceImpl implements TransactionService {

	private final UserAccountService userAccountService;
	private final UserAccountRepository userAccountRepository;
	private final TransactionRepository transactionRepository;

	public TransactionServiceImpl(UserAccountService userAccountService,
								  UserAccountRepository userAccountRepository,
								  TransactionRepository transactionRepository) {

		this.userAccountService = userAccountService;
		this.userAccountRepository = userAccountRepository;
		this.transactionRepository = transactionRepository;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Transaction transferMoney(UUID id, double amount, String description) {
		log.debug("Transfer money to contact: " + id + ",amount: " + amount + ",description: " + description);
		UserAccount userConnected = userAccountService.getPrincipalUser();

		List<UserAccount> contactList = userConnected.getContactList();
		log.debug("Contact list:" + contactList);

		UserAccount creditorAccount =
				contactList.stream()
						.filter(userAccount -> userAccount.getId().equals(id))
						.findFirst()
						.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		double amountWithCommission = amount + (amount * Commission.CHARGES);

		double balanceDebtor = userConnected.getAccount().getBalance();
		double balanceCreditor = creditorAccount.getAccount().getBalance();
		
		if (balanceDebtor < amountWithCommission) {
			throw new DebtorAccountException("Not enough money on your account");
		}
		
		userConnected.getAccount().setBalance(balanceDebtor - amountWithCommission);
		userAccountRepository.save(userConnected);
		creditorAccount.getAccount().setBalance(balanceCreditor + amount);
		userAccountRepository.save(creditorAccount);
		
		Transaction transaction = new Transaction(creditorAccount.getId(), amount, description, "EUR", userConnected, creditorAccount);
		
		transactionRepository.save(transaction);
		
		log.debug("New Transaction = " + transaction);
		
		return transaction;
		
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Transaction transferToBank(double amount, String description) {
		log.debug("Transfer money to my bank account: amount: " + amount + ",description: " + description);
		UserAccount userConnected = userAccountService.getPrincipalUser();

		if(userConnected.getIban() == null) {
			throw new ResourceNotFoundException("Iban doesn't exist");
		}

		double balanceDebtor = userConnected.getAccount().getBalance();

		if(balanceDebtor >= amount) {

			userConnected.getAccount().setBalance(balanceDebtor - amount);

			userAccountRepository.save(userConnected);

			Transaction transaction =  new Transaction(userConnected.getId(), amount, description, "EUR");

			transactionRepository.save(transaction);

			log.debug("New Transaction = " + transaction);

			return transaction;

		} else throw new DebtorAccountException("Not enough money on your account");

	}

	public void addMoney(double amount) {
		UserAccount userConnected = userAccountService.getPrincipalUser();
		if(amount<=0) {
			throw new IllegalArgumentException("Please add a valid value");
		}
		userConnected.getAccount().setBalance(userConnected.getAccount().getBalance() + amount);
		userAccountRepository.save(userConnected);
	}

	public List<Transaction> getAllTransactions() {
		UserAccount userConnected = userAccountService.getPrincipalUser();
		return transactionRepository.findAllByCreditorIdOrDebtorIdOrderByCreationDate(userConnected.getId(), userConnected.getId());
	}
}
