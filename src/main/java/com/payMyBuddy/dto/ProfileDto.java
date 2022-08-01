package com.payMyBuddy.dto;

import com.payMyBuddy.model.AccountStatus;
import com.payMyBuddy.model.UserAccount;
import lombok.Value;

import java.util.UUID;

@Value
public class ProfileDto {
	UUID id;
	String email;
	String firstname;
	String lastname;
	AccountStatus status;
	double balance;
	String iban;

	public ProfileDto(UserAccount userAccount) {
		this.id = userAccount.getId();
		this.email = userAccount.getEmail();
		this.firstname = userAccount.getFirstname();
		this.lastname = userAccount.getLastname();
		this.iban = userAccount.getIban();
		this.status = userAccount.getStatus();
		this.balance = userAccount.getAccount().getBalance();
	}
}
