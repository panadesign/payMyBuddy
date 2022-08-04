package com.payMyBuddy.service.integration;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.exception.UserAlreadyExistException;
import com.payMyBuddy.model.UserAccount;
import com.payMyBuddy.service.UserAccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql("/test-data.sql")
class UserAccountServiceIT {

	@Autowired
	UserAccountService userAccountService;

	@Autowired
	UserAccountRepository userAccountRepository;

	@BeforeEach
	void init() {
		UserAccount userAccount = new UserAccount("mail", "firstname", "lastname", "123");
		userAccountRepository.save(userAccount);
	}

	@Test
	void shouldCreateNewUser() {
		//GIVEN
		UserAccount newUserAccount = new UserAccount("mail1", "firstname", "lastname", "123");
		//WHEN
		userAccountService.registerNewUserAccount(newUserAccount);
		//THEN
		Assertions.assertTrue(userAccountRepository.findByEmail("mail1").isPresent());
	}

	@Test
	void shouldReturnUserAlreadyExistException() {
		UserAccount userToAdd = new UserAccount("c.miossec@mail.com", "firstname2", "lastname2", "123");
		Assertions.assertThrows(UserAlreadyExistException.class, () -> userAccountService.registerNewUserAccount(userToAdd));
	}


}
