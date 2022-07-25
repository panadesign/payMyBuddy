package com.payMyBuddy.service;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.dto.ContactInputDto;
import com.payMyBuddy.exception.RessourceNotFoundException;
import com.payMyBuddy.exception.UnauthorisedUser;
import com.payMyBuddy.model.UserAccount;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class ContactServiceImpl implements ContactService {
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private PrincipalUser principalUser;

		public ContactInputDto addContactByEmail(String email) {
		log.debug("Add new contact to user's connected list");
		UserAccount userConnected = userAccountRepository.findByEmail(principalUser.getCurrentUserName())
				.orElseThrow(() -> new UnauthorisedUser("User unauthorised"));
		UserAccount userAccount = userAccountRepository.findByEmail(email)
				.orElseThrow(() -> new RessourceNotFoundException("User not found with email : " + email));

		if(!(userConnected.getEmail().equals(userAccount.getEmail()))) {
			if(userConnected.getContactList().contains(userAccount)) {
				throw new IllegalArgumentException("This contact is already in your favorites");
			}
			userConnected.getContactList().add(userAccount);
			userAccountRepository.save(userConnected);

			log.debug("New contact has been added to the user's connected list");
			return new ContactInputDto(userAccount);
		} else {
			log.error("User connected can't add his own account to his contact's list");
			throw new IllegalArgumentException("You can't add your user account in your favorites");
		}
	}

	public Boolean removeContactByEmail(String email) {
		log.debug("Remove a contact from user's connected list");
		UserAccount contactToDelete = getContactList()
				.stream()
				.filter(userAccount -> userAccount.getEmail().equals(email))
				.findFirst()
				.orElseThrow(() -> new RessourceNotFoundException("User not found"));

		getContactList().remove(contactToDelete);
		log.debug("Contact has been remove from user's connected list");
		userAccountRepository.save(userAccountService.getPrincipalUser());
		return true;

	}

	public List<UserAccount> getContactList() {
		UserAccount userConnected = userAccountRepository.findByEmail(principalUser.getCurrentUserName())
				.orElseThrow(() -> new RessourceNotFoundException("User not found"));
		log.debug("Get all contacts from user's connected list");
		return userConnected.getContactList();
	}

}
