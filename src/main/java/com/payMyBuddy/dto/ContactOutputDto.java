package com.payMyBuddy.dto;

import com.payMyBuddy.model.UserAccount;
import lombok.*;

@Value
public class ContactOutputDto {
	
	private String email;
	private String firstname;
	private String lastname;
	
	public ContactOutputDto(@NonNull UserAccount userAccount){
		email = userAccount.getEmail();
		firstname = userAccount.getFirstname();
		lastname = userAccount.getLastname();
	}

}