package com.payMyBuddy.dto;

import com.payMyBuddy.model.Account;
import com.payMyBuddy.model.Transaction;
import com.payMyBuddy.model.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInputDto {

	private String email;
	private String firstname;
	private String lastname;
	private Account account;
	private Transaction transaction;

	public ContactInputDto(@NonNull UserAccount userAccount){
		email = userAccount.getEmail();
		firstname = userAccount.getFirstname();
		lastname = userAccount.getLastname();
	}


}
