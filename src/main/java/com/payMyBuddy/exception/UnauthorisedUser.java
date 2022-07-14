package com.payMyBuddy.exception;

public class UnauthorisedUser extends RuntimeException{

	public UnauthorisedUser(String message) {
		super(message);
	}


}
