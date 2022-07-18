package com.payMyBuddy.dto;

import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.Transaction;
import com.payMyBuddy.model.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInputDto {

	private String email;
	private String firstname;
	private String lastname;
	private Account account;
	private Transaction transaction;
//	private LocalDate transactionDate;

	public ContactInputDto(@NonNull UserAccount userAccount){
		this.email = userAccount.getEmail();
		this.firstname = userAccount.getFirstname();
		this.lastname = userAccount.getLastname();
		this.account = userAccount.getAccount();
		this.transaction = userAccount.getAccount().getTransaction();
//		this.transactionDate = userAccount.getAccount().getTransaction().getCreationDate();
	}


}
