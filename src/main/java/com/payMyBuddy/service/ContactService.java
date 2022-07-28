package com.payMyBuddy.service;

import com.payMyBuddy.model.UserAccount;

import java.util.List;

public interface ContactService {
	void addContactByEmail(String email);
	void removeContactByEmail(String email);
	List<UserAccount> getContactList();
}
