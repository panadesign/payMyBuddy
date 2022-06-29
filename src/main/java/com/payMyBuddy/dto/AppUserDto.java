package com.payMyBuddy.dto;

import com.payMyBuddy.model.AccountStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class AppUserDto {
	private UUID id;
	private String email;
	private String firstname;
	private String lastname;
	private String password;
	private AccountStatus status;

	public AppUserDto(String email, String firstname, String lastname, String password) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}

}
