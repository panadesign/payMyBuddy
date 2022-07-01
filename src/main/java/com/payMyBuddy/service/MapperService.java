package com.payMyBuddy.service;

import com.payMyBuddy.dto.FriendDto;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.AccountStatus;
import com.payMyBuddy.model.UserAccount;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

	public ProfileDto convertToProfile(UserAccount userAccount) {
		ProfileDto profileDto = new ProfileDto();
		profileDto.setEmail(userAccount.getEmail());
		profileDto.setFirstname(userAccount.getFirstname());
		profileDto.setLastname(userAccount.getLastname());
		profileDto.setStatus(userAccount.getStatus());
		profileDto.setAccount(userAccount.getAccount());
		profileDto.setBalance(userAccount.getAccount().getBalance());

		return profileDto;
	}

	public FriendDto convertToFriendProfile(UserAccount userAccount) {
		FriendDto friendDto = new FriendDto();
		friendDto.setEmail(userAccount.getEmail());
		friendDto.setFirstname(userAccount.getFirstname());
		friendDto.setLastname(userAccount.getLastname());

		return friendDto;
	}

}
