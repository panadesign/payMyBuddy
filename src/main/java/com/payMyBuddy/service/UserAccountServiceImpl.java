package com.payMyBuddy.service;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.dto.ContactInputDto;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.exception.RessourceNotFoundException;
import com.payMyBuddy.exception.UnauthorisedUser;
import com.payMyBuddy.exception.UserAlreadyExistException;
import com.payMyBuddy.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Component
public class UserAccountServiceImpl implements UserAccountService {
	
	private final UserAccountRepository userAccountRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MapperService mapperService;
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
		ProfileDto profileDto = new ProfileDto(userAccount);

		return profileDto;
	}

	public ContactInputDto getUserAccountById(UUID id) {
		UserAccount userAccount = userAccountRepository.findById(id)
				.orElseThrow(() -> new RessourceNotFoundException("User not found with id : " + id));
		ContactInputDto contactInputDto = new ContactInputDto(userAccount);

		return contactInputDto;
	}
	
	public boolean emailExist(String email) {
		return userAccountRepository.findByEmail(email).isPresent();
	}
	
	public UserAccount registerNewUserAccount(UserAccount userAccount) {
		if (emailExist(userAccount.getEmail())) {
			throw new UserAlreadyExistException("There is an account with that email address:" + userAccount.getEmail());
		}
		String password = passwordEncoder.encode(userAccount.getPassword());
		UserAccount newUserAccount = new UserAccount(userAccount.getEmail(), userAccount.getFirstname(), userAccount.getLastname(), password);
		
		return userAccountRepository.save(newUserAccount);
	}
	
	public UserAccount getPrincipalUser() {
		String currentUserEmail = getCurrentUserEmail();
		return userAccountRepository.findByEmail(currentUserEmail)
				.orElseThrow(() -> new RessourceNotFoundException("User not found"));
	}
	
	
	protected String getCurrentUserEmail() {
		String currentUserEmail = principalUser.getCurrentUserEmail();
		
		if (currentUserEmail.isEmpty()) {
			throw new UnauthorisedUser("Unauthorised user");
		}
		return currentUserEmail;
	}
}
