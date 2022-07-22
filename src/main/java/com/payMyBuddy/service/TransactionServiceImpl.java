package com.payMyBuddy.service;

import com.payMyBuddy.constants.Commission;
import com.payMyBuddy.dao.TransactionRepository;
import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.exception.DebtorAccountException;
import com.payMyBuddy.exception.RessourceNotFoundException;
import com.payMyBuddy.model.Transaction;
import com.payMyBuddy.model.UserAccount;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Log4j2
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	public Transaction transferMoney(UUID id, double amount, String description) {
		log.debug("Transfer money to contact");
		UserAccount userConnected = userAccountService.getPrincipalUser();

		List<UserAccount> contactList = userConnected.getContactList();
		
		UserAccount creditorAccount =
				contactList.stream()
						.filter(userAccount -> userAccount.getId().equals(id))
						.findFirst()
						.orElseThrow(() -> new RessourceNotFoundException("User not found"));

		double amountWithCommission = amount + (amount * Commission.COMMISSION);

		double balanceDebtor = userConnected.getAccount().getBalance();
		double balanceCreditor = creditorAccount.getAccount().getBalance();

		if(balanceDebtor >= amountWithCommission) {

			userConnected.getAccount().setBalance(balanceDebtor - amountWithCommission);
			creditorAccount.getAccount().setBalance(balanceCreditor + amount);

			userAccountRepository.save(userConnected);
			userAccountRepository.save(creditorAccount);

			Transaction transaction =  new Transaction(userConnected, creditorAccount, amount , "EUR", description );

			transactionRepository.save(transaction);
			
			log.debug("New Transaction = " + transaction);

			return transaction;

		} else throw new DebtorAccountException("Not enough money on your account");

	}

	public List<Transaction> getAllTransactions() {
		UserAccount userConnected = userAccountService.getPrincipalUser();

		return transactionRepository.findAllByCreditorIdOrDebtorIdOrderByCreationDate(userConnected.getId(), userConnected.getId());
	}
}
