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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Sql("/data.sql")
@Transactional
public class TransferControllerTest {
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
	@WithUserDetails("a@email")
	void postTransfer() throws Exception {

		Map<String, Object> attributs = new HashMap<>();
		attributs.put("id" , UUID.randomUUID()); // uuid de B
		attributs.put("amount", 2.5);
		attributs.put("description", "blabla");

		mockMvc.perform(
						post("/transaction")
								.with(csrf())
								.flashAttrs(attributs))
				.andDo(MockMvcResultHandlers.print())

				.andExpect(flash().attributeExists("transaction", "transactionSuccess"))
				.andExpect(flash().attribute("transactionSuccess", "Your transaction is successfully added"))

				.andExpect(redirectedUrl("/transaction"));

	}
}
