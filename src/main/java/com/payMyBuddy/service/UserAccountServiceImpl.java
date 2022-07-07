package com.payMyBuddy.service;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.dto.UserAccountDto;
import com.payMyBuddy.exception.RessourceNotFoundException;
import com.payMyBuddy.exception.UserAlreadyExistException;
import com.payMyBuddy.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class UserAccountServiceImpl implements UserAccountService{

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MapperService mapperService;

	private final UserAccountRepository userAccountRepository;

	public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}

	public List<UserAccount> getAllUsersAccount() {
		return userAccountRepository.findAll();
	}

	public ProfileDto getUserAccountByEmail(String email) {
		UserAccount userAccount = userAccountRepository.findByEmail(email)
				.orElseThrow(() -> new RessourceNotFoundException("User not found with email : " + email));
		return mapperService.convertUserAccountToProfileDto(userAccount);
	}

	private boolean emailExist(String email) {
		return userAccountRepository.findByEmail(email).isPresent();
	}

	public UserAccount registerNewUserAccount(UserAccountDto userAccountDto) {
		if(emailExist(userAccountDto.getEmail())) {
			throw new UserAlreadyExistException("There is an account with that email address:" + userAccountDto.getEmail());
		}
		String password = passwordEncoder.encode(userAccountDto.getPassword());
		UserAccount userAccount = new UserAccount(userAccountDto.getEmail(), userAccountDto.getFirstname(), userAccountDto.getLastname(), password);

		return userAccountRepository.save(userAccount);
	}
}
