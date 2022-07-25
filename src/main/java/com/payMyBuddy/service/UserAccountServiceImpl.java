package com.payMyBuddy.service;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.exception.RessourceNotFoundException;
import com.payMyBuddy.exception.UnauthorisedUser;
import com.payMyBuddy.exception.UserAlreadyExistException;
import com.payMyBuddy.model.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
@Slf4j
public class UserAccountServiceImpl implements UserAccountService {

	private final UserAccountRepository userAccountRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private PrincipalUser principalUser;

	public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}

	public List<UserAccount> getAllUsersAccount() {
		return userAccountRepository.findAll();
	}

	public ProfileDto getUserAccountByEmail(String email) {
		UserAccount userAccount = userAccountRepository.findByEmail(email)
				.orElseThrow(() -> new RessourceNotFoundException("User not found with email : " + email));

		return new ProfileDto(userAccount);
	}

	private boolean emailExist(String email) {
		return userAccountRepository.findByEmail(email).isPresent();
	}

	public UserAccount registerNewUserAccount(UserAccount userAccount) {
		if(emailExist(userAccount.getEmail())) {
			throw new UserAlreadyExistException("There is an account with that email address:" + userAccount.getEmail());
		}
		String password = passwordEncoder.encode(userAccount.getPassword());
		UserAccount newUserAccount = new UserAccount(userAccount.getEmail(), userAccount.getFirstname(), userAccount.getLastname(), password);
		log.debug("New account : " + newUserAccount);
		
		return userAccountRepository.save(newUserAccount);
	}

	public UserAccount getPrincipalUser() {
		String currentUserEmail = getCurrentUserEmail();
		return userAccountRepository.findByEmail(currentUserEmail)
				.orElseThrow(() -> new RessourceNotFoundException("User not found"));
	}


	private String getCurrentUserEmail() {
		String currentUserEmail = principalUser.getCurrentUserName();

		if(currentUserEmail.isEmpty()) {
			throw new UnauthorisedUser("Unauthorised user");
		}
		return currentUserEmail;
	}
}
