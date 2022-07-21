package com.payMyBuddy.service;

import com.payMyBuddy.dto.ContactInputDto;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.model.UserAccount;

import java.util.List;
import java.util.UUID;

public interface UserAccountService {

	List<UserAccount> getAllUsersAccount();
	ProfileDto getUserAccountByEmail(String email);
	ContactInputDto getUserAccountById(UUID id);
	UserAccount registerNewUserAccount(UserAccount userAccount);
	UserAccount getPrincipalUser();
	boolean emailExist(String email);
}
