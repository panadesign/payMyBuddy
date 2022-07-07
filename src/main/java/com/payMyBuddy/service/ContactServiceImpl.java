package com.payMyBuddy.service;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.dto.ContactDto;
import com.payMyBuddy.exception.RessourceNotFoundException;
import com.payMyBuddy.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactServiceImpl implements ContactService {
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Autowired
	private MapperService mapperService;
	@Autowired
	private PrincipalUser principalUser;

	public ContactDto addContactByEmail(String email) {

		UserAccount userConnected = userAccountRepository.findByEmail(principalUser.getCurrentUserEmail()).get();
		UserAccount userAccount = userAccountRepository.findByEmail(email)
				.orElseThrow(() -> new RessourceNotFoundException("User not found with email : " + email));

		if(!(userConnected.getEmail().equals(userAccount.getEmail()))) {
			if(userConnected.getContactList().contains(userAccount)) {
				throw new IllegalArgumentException("This contact is already in your favorites");
			}
			userConnected.getContactList().add(userAccount);
			userAccountRepository.save(userConnected);

			return mapperService.convertUserAccountToContactDto(userAccount);
		} else {
			throw new IllegalArgumentException("You can't add your account in your favorites");
		}
	}

	public List<UserAccount> getContact() {
		UserAccount userConnected = userAccountRepository.findByEmail(principalUser.getCurrentUserEmail()).get();
		return userConnected.getContactList();
	}

}
