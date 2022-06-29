package com.payMyBuddy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileDto {

	private String email;
	private String firstName;
	private String lastName;
}
