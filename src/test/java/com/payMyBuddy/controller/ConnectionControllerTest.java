package com.payMyBuddy.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Sql("/test-data.sql")
@Transactional
class ConnectionControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.apply(springSecurity())
				.build();
	}

	@Test
	@WithUserDetails("c.miossec@mail.com")
	void addContact() throws Exception {
		mockMvc.perform(
						post("/addConnection")
								.with(csrf())
								.param("email", "g.brassens@mail.com")
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/transaction"));

	}

	@Test
	@WithUserDetails("c.miossec@mail.com")
	void deleteContact() throws Exception {
		mockMvc.perform(
						get("/deleteConnection")
								.with(csrf())
								.param("email", "a.sylvestre@mail.com")
				)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/profile"));

	}
}