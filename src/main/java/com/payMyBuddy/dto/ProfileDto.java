package com.payMyBuddy.dto;

import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.AccountStatus;
import com.payMyBuddy.model.UserAccount;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ProfileDto {
	private UUID id;
	private String email;
	private String firstname;
	private String lastname;
	private AccountStatus status;
	private Account account;
	private float balance;
	private List<UserAccount> userAccountList;

	public ProfileDto(UserAccount userAccount) {
		this.id = userAccount.getId();
		this.email = userAccount.getEmail();
		this.firstname = userAccount.getFirstname();
		this.lastname = userAccount.getLastname();
	}

	public ProfileDto() {

	}
}
