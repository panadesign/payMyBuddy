package com.payMyBuddy.integration;

import com.payMyBuddy.dao.UserAccountRepository;
import com.payMyBuddy.service.UserAccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class UserAccountServiceIT {

	@Autowired
	UserAccountService userAccountService;
	@Autowired
	UserAccountRepository userAccountRepository;

}
