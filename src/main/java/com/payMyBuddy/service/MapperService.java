package com.payMyBuddy.service;

import com.payMyBuddy.dto.ContactDto;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.model.UserAccount;

import java.util.List;

public interface MapperService {
	ProfileDto convertUserAccountToProfileDto(UserAccount userAccount);
	ContactDto convertUserAccountToContactDto(UserAccount userAccount);
	List<ContactDto> convertUserAccountListToContactDtoList(List<UserAccount> userAccountList);
}
