package com.payMyBuddy.exception;

public class UserAlreadyExistException extends RuntimeException{
	public UserAlreadyExistException(String message) {
		super(String.format("User with this email already exist", message));;
	}
}
