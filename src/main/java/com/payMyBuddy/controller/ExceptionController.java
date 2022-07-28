package com.payMyBuddy.controller;

import com.payMyBuddy.exception.DebtorAccountException;
import com.payMyBuddy.exception.RessourceNotFoundException;
import com.payMyBuddy.exception.UnauthorisedUserException;
import com.payMyBuddy.exception.UserAlreadyExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(UnauthorisedUserException.class)
	public String unauthorisedUserException(UnauthorisedUserException ex) {
		return "error";
	}

	@ExceptionHandler(UserAlreadyExistException.class)
	public String userAlreadyExistException(UserAlreadyExistException ex) {
		return "error";
	}

	@ExceptionHandler(DebtorAccountException.class)
	public String debtorAccountException(DebtorAccountException ex) {
		return "error";
	}

	@ExceptionHandler(RessourceNotFoundException.class)
	public String ressourceNotFoundException(RessourceNotFoundException ex) {
		return "error";
	}
}
