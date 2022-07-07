package com.payMyBuddy.dto;

import com.payMyBuddy.model.UserAccount;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class ContactOutputDto {
	
	private final String email;
	private final String firstname;
	private final String lastname;
	
	public ContactOutputDto(@NonNull UserAccount userAccount){
		email = userAccount.getEmail();
		firstname = userAccount.getFirstname();
		lastname = userAccount.getLastname();
	}
	
}