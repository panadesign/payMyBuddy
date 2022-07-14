package com.payMyBuddy.service;

import com.payMyBuddy.dto.ContactDto;
import com.payMyBuddy.dto.ContactInputDto;
import com.payMyBuddy.dto.ContactOutputDto;
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
		profileDto.setBalance((float) userAccount.getAccount().getBalance());

		return profileDto;
	}

	public ContactInputDto convertUserAccountToContactOutputDto(UserAccount userAccount) {
		ContactInputDto contactInputDto = new ContactInputDto();
		contactInputDto.setEmail(userAccount.getEmail());
		contactInputDto.setFirstname(userAccount.getFirstname());
		contactInputDto.setLastname(userAccount.getLastname());

		return contactInputDto;
	}
}
