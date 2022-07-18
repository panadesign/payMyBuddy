package com.payMyBuddy.service;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.dto.ContactDto;
import com.payMyBuddy.dto.ContactInputDto;
import com.payMyBuddy.dto.UserAccountDto;
import com.payMyBuddy.exception.RessourceNotFoundException;
import com.payMyBuddy.exception.UnauthorisedUser;
import com.payMyBuddy.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactServiceImpl implements ContactService {
	@Autowired
	private UserAccountRepository userAccountRepository;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private MapperService mapperService;
	@Autowired
	private PrincipalUser principalUser;

	public ContactInputDto addContactByEmail(String email) {

		UserAccount userConnected = userAccountRepository.findByEmail(principalUser.getCurrentUserEmail())
				.orElseThrow(() -> new UnauthorisedUser("User unauthorised"));
		UserAccount userAccount = userAccountRepository.findByEmail(email)
				.orElseThrow(() -> new RessourceNotFoundException("User not found with email : " + email));

		if(!(userConnected.getEmail().equals(userAccount.getEmail()))) {
			if(userConnected.getContactList().contains(userAccount)) {
				throw new IllegalArgumentException("This contact is already in your favorites");
			}
			userConnected.getContactList().add(userAccount);
			userAccountRepository.save(userConnected);

			return mapperService.convertUserAccountToContactOutputDto(userAccount);
		} else {
			throw new IllegalArgumentException("You can't add your user account in your favorites");
		}
	}

	public Boolean removeContactByEmail(String email) {
		UserAccount contactToDelete = getContactList()
				.stream()
				.filter(userAccount -> userAccount.getEmail().equals(email))
				.findFirst()
				.orElseThrow(() -> new RessourceNotFoundException("User not found"));

		getContactList().remove(contactToDelete);
		userAccountRepository.save(userAccountService.getPrincipalUser());
		return true;

	}

	public List<UserAccount> getContactList() {
		UserAccount userConnected = userAccountRepository.findByEmail(principalUser.getCurrentUserEmail())
				.orElseThrow(() -> new RessourceNotFoundException("User not found"));

		return userConnected.getContactList();
	}

}
