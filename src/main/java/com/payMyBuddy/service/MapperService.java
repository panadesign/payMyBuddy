package com.payMyBuddy.service;

import com.payMyBuddy.dto.ContactInputDto;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.model.UserAccount;

public interface MapperService {
	ProfileDto convertUserAccountToProfileDto(UserAccount userAccount);
	ContactInputDto convertUserAccountToContactInputDto(UserAccount userAccount);
}
