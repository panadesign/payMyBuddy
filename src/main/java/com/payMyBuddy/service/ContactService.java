package com.payMyBuddy.service;

import com.payMyBuddy.dto.ContactDto;
import com.payMyBuddy.model.UserAccount;

import java.util.List;

public interface ContactService {
	ContactDto addContactByEmail(String email);
	List<UserAccount> getContactList();
}
