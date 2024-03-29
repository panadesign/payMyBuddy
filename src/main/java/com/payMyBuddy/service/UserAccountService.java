package com.payMyBuddy.service;

import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.model.UserAccount;

import java.util.List;

public interface UserAccountService {

	List<UserAccount> getAllUsersAccount();
	ProfileDto getUserAccountByEmail(String email);
	UserAccount registerNewUserAccount(UserAccount userAccount);
	UserAccount getPrincipalUser();
	void addIban(String iban);
}
