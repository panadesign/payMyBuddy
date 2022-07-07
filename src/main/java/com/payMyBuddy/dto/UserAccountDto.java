package com.payMyBuddy.dto;

import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.AccountStatus;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserAccountDto {
	private UUID id;
	private String email;
	private String firstname;
	private String lastname;
	private String password;
	private AccountStatus status;
	private Account account;
	private List<UserAccountDto> userAccountList;

	public UserAccountDto(String email, String firstname, String lastname, String password) {

		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;

	}

	public UserAccountDto(String email, String firstname, String lastname) {

		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;

	}

}
