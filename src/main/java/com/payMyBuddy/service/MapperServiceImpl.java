package com.payMyBuddy.service;

import com.payMyBuddy.dto.ContactDto;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.model.UserAccount;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapperServiceImpl implements MapperService{

	public ProfileDto convertUserAccountToProfileDto(UserAccount userAccount) {
		ProfileDto profileDto = new ProfileDto();
		profileDto.setEmail(userAccount.getEmail());
		profileDto.setFirstname(userAccount.getFirstname());
		profileDto.setLastname(userAccount.getLastname());
		profileDto.setStatus(userAccount.getStatus());
		profileDto.setAccount(userAccount.getAccount());
		profileDto.setBalance(userAccount.getAccount().getBalance());

		return profileDto;
	}

	public ContactDto convertUserAccountToContactDto(UserAccount userAccount) {
		ContactDto contactDto = new ContactDto();
		contactDto.setEmail(userAccount.getEmail());
		contactDto.setFirstname(userAccount.getFirstname());
		contactDto.setLastname(userAccount.getLastname());

		return contactDto;
	}

	public List<ContactDto> convertUserAccountListToContactDtoList(List<UserAccount> userAccountList) {
		return userAccountList.stream()
				.map(userAccount -> new ContactDto(
						userAccount.getEmail(),
						userAccount.getFirstname(),
						userAccount.getLastname()))
				.collect(Collectors.toList());
	}
}
