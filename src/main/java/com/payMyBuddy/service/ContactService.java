package com.payMyBuddy.service;

import com.payMyBuddy.dto.ContactDto;
import com.payMyBuddy.dto.ContactInputDto;
import com.payMyBuddy.model.UserAccount;

import java.util.List;

public interface ContactService {
	ContactInputDto addContactByEmail(String email);
	Boolean removeContactByEmail(String email);
	List<UserAccount> getContactList();
}
