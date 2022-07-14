package com.payMyBuddy.dto;

import com.payMyBuddy.model.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
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