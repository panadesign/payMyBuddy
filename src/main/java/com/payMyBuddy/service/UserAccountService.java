package com.payMyBuddy.service;

import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.model.UserAccount;

import java.util.List;

public interface UserAccountService {

	List<UserAccount> getAllUsersAccount();
	ProfileDto getUserAccountByEmail(String email);
//	ContactInputDto getUserAccountById(UUID id);
	UserAccount registerNewUserAccount(UserAccount userAccount);
	UserAccount getPrincipalUser();
}
