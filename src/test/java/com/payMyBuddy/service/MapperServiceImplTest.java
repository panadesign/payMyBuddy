package com.payMyBuddy.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class MapperServiceImplTest {

	@Autowired
	MapperService mapperService;

//	@Test
//	void convertUserAccountToProfileDto() {
//		UserAccount userAccountToConvert = new UserAccount("email@test.com", "firstname", "lastname", "password");
//		ProfileDto profileDto = mapperService.convertUserAccountToProfileDto(userAccountToConvert);
//
//		Assertions.assertTrue(profileDto.getEmail().equals(userAccountToConvert.getEmail()));
//	}

	@Test
	void convertUserAccountToContactInputDto() {
	}

	@Test
	void contactInputDtoToUserAccount() {
	}
}