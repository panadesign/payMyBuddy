package com.payMyBuddy.service;

import com.payMyBuddy.dto.ContactInputDto;
import com.payMyBuddy.model.UserAccount;

//ToDo a supprimer
public interface MapperService {
	ContactInputDto convertUserAccountToContactInputDto(UserAccount userAccount);
}
