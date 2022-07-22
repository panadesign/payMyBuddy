package com.payMyBuddy.dto;

import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.UserAccount;
import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

@Value
public class ContactInputDto {

	UUID id;
	String email;
	String firstname;
	String lastname;
	Account account;


	public ContactInputDto(@NonNull UserAccount userAccount){
		this.id = userAccount.getId();
		this.email = userAccount.getEmail();
		this.firstname = userAccount.getFirstname();
		this.lastname = userAccount.getLastname();
		this.account = userAccount.getAccount();

	}


}
