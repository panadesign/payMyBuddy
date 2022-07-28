package com.payMyBuddy.service;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.exception.UnauthorisedUserException;
import com.payMyBuddy.exception.UserAlreadyExistException;
import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.AccountStatus;
import com.payMyBuddy.model.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceImplTest {

	private UserAccountService userAccountService;

	@Mock
	private UserAccountRepository mockUserAccountRepository;
	@Mock
	private PasswordEncoder mockPasswordEncoder;
	@Mock
	private PrincipalUser mockPrincipalUser;

	@BeforeEach
	public void Init() {
		userAccountService = new UserAccountServiceImpl(mockUserAccountRepository, mockPrincipalUser, mockPasswordEncoder);
	}


	@Test
	void getAllUsersAccount() {
		//GIVEN
		List<UserAccount> appUsers = new ArrayList<>();
		appUsers.add(new UserAccount("email1", "prenom1", "nom1", "password1"));
		appUsers.add(new UserAccount("email2", "prenom2", "nom2", "password2"));
		appUsers.add(new UserAccount("email3", "prenom3", "nom3", "password3"));

		//WHEN
		when(mockUserAccountRepository.findAll()).thenReturn(appUsers);
		List<UserAccount> allAppUsers = userAccountService.getAllUsersAccount();

		//THEN
		Assertions.assertEquals(appUsers, allAppUsers);
	}

	@Test
	void getUserAccountByEmail() {
		//GIVEN
		UserAccount userAccount = new UserAccount(UUID.randomUUID(), "email@test.com", "jeremy", "charpentier", "'password", AccountStatus.ACTIVE, new Account());

		//WHEN
		when(mockUserAccountRepository.findByEmail(userAccount.getEmail())).thenReturn(Optional.of(userAccount));
		ProfileDto userAccountToDto = new ProfileDto(userAccount);
		ProfileDto user = userAccountService.getUserAccountByEmail("email@test.com");

		//THEN
		Assertions.assertEquals(user, userAccountToDto);
	}


	@Test
	void getPrincipalUser() {

		UserAccount userAccount = new UserAccount(UUID.randomUUID(), "email@test.com", "jeremy", "charpentier", "'password", AccountStatus.ACTIVE, new Account());
		when(mockPrincipalUser.getCurrentUserName()).thenReturn("email@test.com");
		when(mockUserAccountRepository.findByEmail(userAccount.getEmail())).thenReturn(Optional.of(userAccount));

		UserAccount principalUSer = userAccountService.getPrincipalUser();

		Assertions.assertEquals(userAccount, principalUSer);
	}

	@Test
	void getPrincipalUserWithCurrentUSerEMailEmpty() {
		when(mockPrincipalUser.getCurrentUserName()).thenReturn("");

		Assertions.assertThrows(UnauthorisedUserException.class, () -> userAccountService.getPrincipalUser());
	}

	@Test
	void registerNewUserAccount() {
		//GIVEN
		String email = "connected@mail.com";
		String firstname = "firstname1";
		String lastname = "lastname1";
		String plainTextPassword = "123";
		String hashedPassword = "456";


		UserAccount userToRegister = new UserAccount(
				UUID.randomUUID(),
				email,
				firstname,
				lastname,
				plainTextPassword,
				AccountStatus.ACTIVE,
				new Account());

		//WHEN
		when(mockUserAccountRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

		when(mockPasswordEncoder.encode(any())).thenReturn(hashedPassword);

		UserAccount newUser = userAccountService.registerNewUserAccount(userToRegister);

		//THEN
		assertThat(newUser)
				.satisfies( u -> {
					assertThat(u.getEmail()).hasToString(email);
					assertThat(u.getFirstname()).hasToString(firstname);
					assertThat(u.getLastname()).hasToString(lastname);
					assertThat(u.getPassword()).hasToString(hashedPassword);
				});

	}

	@Test
	void registerNewUserAccountEmailAlreadyExist() {
		//GIVEN
		UserAccount userAlreadyRegistered = new UserAccount(UUID.randomUUID(), "userInDataBase@mail.com", "firstname1", "lastname1", "123", AccountStatus.ACTIVE, new Account());
		UserAccount userToRegister = new UserAccount(UUID.randomUUID(), "userInDataBase@mail.com", "firstname1", "lastname1", "123", AccountStatus.ACTIVE, new Account());

		//WHEN
		when(mockUserAccountRepository.findByEmail("userInDataBase@mail.com")).thenReturn(Optional.of(userAlreadyRegistered));

		//THEN
		Assertions.assertThrows(UserAlreadyExistException.class, () -> userAccountService.registerNewUserAccount(userToRegister));
	}
}