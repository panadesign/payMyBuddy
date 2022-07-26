package com.payMyBuddy.service;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.dto.ProfileDto;
import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.AccountStatus;
import com.payMyBuddy.model.UserAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceImplTest {

	private UserAccountService userAccountService;

	@BeforeEach
	public void Init() {
		userAccountService = new UserAccountServiceImpl(mockUserAccountRepository);
	}

	@Mock
	private UserAccountRepository mockUserAccountRepository;
	@Mock
	private PrincipalUser mockPrincipalUser;
	@Mock
	private UserAccountService mcoKUserAccountService;

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

		Assertions.assertTrue(principalUSer.equals(userAccount));
	}

	@Test
	void registerNewUserAccount() {
		//GIVEN
		UserAccount userToRegister = new UserAccount(UUID.randomUUID(), "connected@mail.com", "firstname1", "lastname1", "123", AccountStatus.ACTIVE, new Account());

		//WHEN
		when(mockUserAccountRepository.save(any())).thenReturn(userToRegister);
		UserAccount newUser = userAccountService.registerNewUserAccount(userToRegister);

		//THEN
		Assertions.assertTrue(newUser.getEmail().equals("connected@mail.com"));
	}

//	@Test
//	void registerNewUserAccountEmailAlreadyExist() {
//		//GIVEN
//		UserAccount userToRegister = new UserAccount(UUID.randomUUID(), "userInDataBase@mail.com", "firstname1", "lastname1", "123", AccountStatus.ACTIVE, new Account());
//
//		//WHEN
//
//		when(userAccountService.)
//		when(mockUserAccountRepository.save(any())).thenReturn(userToRegister);
//
//		//THEN
//		Assertions.assertThrows(UserAlreadyExistException.class, () -> userAccountService.registerNewUserAccount(userToRegister));
//	}
}