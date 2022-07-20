package com.payMyBuddy.service;

import com.payMyBuddy.dto.ContactInputDto;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.model.UserAccount;
import org.springframework.stereotype.Service;

@Service
public class MapperServiceImpl implements MapperService{

	public ProfileDto convertUserAccountToProfileDto(UserAccount userAccount) {
		ProfileDto profileDto = new ProfileDto();
		profileDto.setEmail(userAccount.getEmail());
		profileDto.setFirstname(userAccount.getFirstname());
		profileDto.setLastname(userAccount.getLastname());
		profileDto.setStatus(userAccount.getStatus());
		profileDto.setAccount(userAccount.getAccount());
		profileDto.setBalance((float) userAccount.getAccount().getBalance());

		return profileDto;
	}

	public ContactInputDto convertUserAccountToContactInputDto(UserAccount userAccount) {
		ContactInputDto contactInputDto = new ContactInputDto();

		contactInputDto.setEmail(userAccount.getEmail());
		contactInputDto.setFirstname(userAccount.getFirstname());
		contactInputDto.setLastname(userAccount.getLastname());

		return contactInputDto;
	}

	public UserAccount contactInputDtoToUserAccount(ContactInputDto contactInputDto) {
		UserAccount userAccount = new UserAccount();
		userAccount.setId(contactInputDto.getId());
		userAccount.setEmail(contactInputDto.getEmail());
		userAccount.setFirstname(contactInputDto.getFirstname());
		userAccount.setLastname(contactInputDto.getLastname());
		userAccount.setAccount(contactInputDto.getAccount());

		return userAccount;
	}
}
