package com.payMyBuddy.controller;

import com.payMyBuddy.service.UserAccountService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "file:src/test/resources/application-context.xml")
@WebMvcTest(controllers = LoginController.class)
class LoginControllerTest {

	@Mock
	private MockMvc mockMvc;

	@MockBean
	UserAccountService userAccountService;

	@Test
	void login() throws Exception {
		mockMvc.perform(get("/login"))
				.andExpect(status().isOk());
	}

}