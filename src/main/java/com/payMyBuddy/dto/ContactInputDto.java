package com.payMyBuddy.dto;

import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.Transaction;
import com.payMyBuddy.model.UserAccount;
import lombok.*;

import java.util.UUID;

@Value
public class ContactInputDto {

	private UUID id;
	private String email;
	private String firstname;
	private String lastname;
	private Account account;
	private Transaction transaction;

	public ContactInputDto(@NonNull UserAccount userAccount){
		this.id = userAccount.getId();
		this.email = userAccount.getEmail();
		this.firstname = userAccount.getFirstname();
		this.lastname = userAccount.getLastname();
		this.account = userAccount.getAccount();
		this.transaction = userAccount.getAccount().getTransaction();
	}


}
