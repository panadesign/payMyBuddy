package com.payMyBuddy.dto;

import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.AccountStatus;
import com.payMyBuddy.model.UserAccount;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserAccountDto {
	private UUID id;
	private String email;
	private String firstname;
	private String lastname;
	private String password; // -> NON !!!!
	private AccountStatus status;
	private Account account;
	private List<UserAccountDto> userAccountList;

	public UserAccountDto (UserAccount userAccount){
		this.id = userAccount.getId();
		this.email= userAccount.getEmail();
		this.firstname = userAccount.getFirstname();
		this.lastname = userAccount.getLastname();
	}

}
