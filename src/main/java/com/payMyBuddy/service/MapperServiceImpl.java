package com.payMyBuddy.service;

import com.payMyBuddy.dto.ContactInputDto;
import com.payMyBuddy.model.UserAccount;
import org.springframework.stereotype.Service;

@Service

//ToDo a supprimer
public class MapperServiceImpl implements MapperService{

	public ContactInputDto convertUserAccountToContactInputDto(UserAccount userAccount) {
		ContactInputDto contactInputDto = new ContactInputDto();

		contactInputDto.setEmail(userAccount.getEmail());
		contactInputDto.setFirstname(userAccount.getFirstname());
		contactInputDto.setLastname(userAccount.getLastname());

		return contactInputDto;
	}
}
