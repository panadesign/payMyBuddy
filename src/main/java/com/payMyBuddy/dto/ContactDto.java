package com.payMyBuddy.dto;

import com.payMyBuddy.model.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {

	private UUID id;
	private String email;
	private String firstname;
	private String lastname;

	ContactDto(UserAccount userAccount) {
		this.id = userAccount.getId();
		this.email = userAccount.getEmail();
		this.firstname = userAccount.getFirstname();
		this.lastname = userAccount.getLastname();
	}

}
