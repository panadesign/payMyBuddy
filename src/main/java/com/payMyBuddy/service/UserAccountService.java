package com.payMyBuddy.service;

import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.dto.UserAccountDto;
import com.payMyBuddy.model.UserAccount;

import java.util.List;

public interface UserAccountService {

	List<UserAccount> getAllUsersAccount();
	ProfileDto getUserAccountByEmail(String email);
	UserAccount registerNewUserAccount(UserAccountDto userAccountDto);
	UserAccount getPrincipalUser();
}
