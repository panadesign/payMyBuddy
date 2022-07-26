package com.payMyBuddy.controller;

import com.payMyBuddy.service.UserAccountService;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

	@Mock
	private MockMvc mockMvc;

	@MockBean
	UserAccountService userAccountService;

//	@Test
//	void login() throws Exception {
//		mockMvc.perform(get("/login"))
//				.andExpect(status().isOk());
//	}

}