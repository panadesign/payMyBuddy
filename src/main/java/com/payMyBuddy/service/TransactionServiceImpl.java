package com.payMyBuddy.service;

import com.payMyBuddy.constants.Commission;
import com.payMyBuddy.dao.TransactionRepository;
import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.dto.ContactInputDto;
import com.payMyBuddy.exception.DebtorAccountException;
import com.payMyBuddy.exception.RessourceNotFoundException;
import com.payMyBuddy.model.Transaction;
import com.payMyBuddy.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	public Transaction transferMoney(String email, double amount, String description) {

		UserAccount userConnected = userAccountService.getPrincipalUser();

		//TODO modifier par appel DTO
		List<UserAccount> contactList = userConnected.getContactList();
		ContactInputDto creditor =
				contactList.stream()
						.filter(userAccount -> userAccount.getEmail().equals(email))
						.findFirst()
						.map(contact -> new ContactInputDto(contact.getEmail(), contact.getFirstname(), contact.getLastname(), contact.getAccount(), contact.getAccount().getTransaction()))
						.orElseThrow(() -> new RessourceNotFoundException("User not found"));

		double amountWithCommission = amount + (amount * Commission.COMMISSION);
		double amountForCreditor = amount;

		double balanceDebtor = userConnected.getAccount().getBalance();
		double balanceCreditor = userAccountService.getUserAccountByEmail(email).getBalance();


		if(balanceDebtor >= amountWithCommission) {

			userConnected.getAccount().setBalance(balanceDebtor - amountWithCommission);
			creditor.getAccount().setBalance(balanceCreditor + amountForCreditor);
//			creditor.getAccount().getTransaction().setCreationDate(LocalDate.now());
//			creditor.getAccount().getTransaction().setDescription(description);

			userAccountRepository.save(userConnected);
//			userAccountRepository.save(creditor);

		} else throw new DebtorAccountException("Not enough money on your account");
		return null;
	}

	public List<Transaction> getAllTransactions() {
		UserAccount userConnected = userAccountService.getPrincipalUser();

		//TODO Créer la méthode pour récupérer toutes les transactions


		return null;
	}
}
