package com.payMyBuddy.dto;

import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.AccountStatus;
import com.payMyBuddy.model.UserAccount;
import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {
	private String email;
	private String firstname;
	private String lastname;
	private AccountStatus status;
	private Account account;
	private float balance;
	private List<UserAccount> userAccountList;
}
