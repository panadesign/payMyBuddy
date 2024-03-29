package com.payMyBuddy.service;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.exception.ResourceNotFoundException;
import com.payMyBuddy.exception.UnauthorisedUserException;
import com.payMyBuddy.exception.UserAlreadyExistException;
import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.AccountStatus;
import com.payMyBuddy.model.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
@Slf4j
public class UserAccountServiceImpl implements UserAccountService {

	private final UserAccountRepository userAccountRepository;

	private final PasswordEncoder passwordEncoder;

	private final PrincipalUser principalUser;

	public UserAccountServiceImpl(UserAccountRepository userAccountRepository,
								  PrincipalUser principalUser,
								  PasswordEncoder passwordEncoder) {
		this.userAccountRepository = userAccountRepository;
		this.principalUser = principalUser;
		this.passwordEncoder = passwordEncoder;
	}

	public List<UserAccount> getAllUsersAccount() {
		return userAccountRepository.findAll();
	}

	public ProfileDto getUserAccountByEmail(String email) {
		UserAccount userAccount = userAccountRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with email : " + email));

		return new ProfileDto(userAccount);
	}

	public UserAccount registerNewUserAccount(UserAccount userAccount) {
		if(emailExist(userAccount.getEmail())) {
			throw new UserAlreadyExistException("An account with this email address already exist:" + userAccount.getEmail());
		}

		String password = passwordEncoder.encode(userAccount.getPassword());

		UserAccount newUserAccount = new UserAccount(userAccount.getId(), userAccount.getEmail(), userAccount.getFirstname(), userAccount.getLastname(), password, userAccount.getIban(), AccountStatus.ACTIVE, new Account(), new ArrayList<>());
		log.debug("New account : " + newUserAccount);
		return userAccountRepository.save(newUserAccount);
	}

	public UserAccount getPrincipalUser() {
		String currentUserEmail = getCurrentUserEmail();
		log.debug("Principal user: " + userAccountRepository.findByEmail(currentUserEmail));
		return userAccountRepository.findByEmail(currentUserEmail)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
	}

	public void addIban(String iban) {
		UserAccount connectedUser = getPrincipalUser();
		connectedUser.setIban(iban);
		userAccountRepository.save(connectedUser);
	}

	private String getCurrentUserEmail() {
		String currentUserEmail = principalUser.getCurrentUserName();

		if(currentUserEmail.isEmpty()) {
			throw new UnauthorisedUserException("Unauthorised user");
		}
		log.debug("Current user email: " + currentUserEmail);
		return currentUserEmail;
	}

	private boolean emailExist(String email) {
		return userAccountRepository.findByEmail(email).isPresent();
	}
}
