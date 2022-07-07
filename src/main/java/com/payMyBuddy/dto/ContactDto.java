package com.payMyBuddy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {

	private String email;
	private String firstname;
	private String lastname;

}
