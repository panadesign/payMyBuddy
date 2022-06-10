package com.PayMyBuddy.PayMyBuddy.model;

import java.util.UUID;

public class Account {

	private UUID id;
	private int accountNumber;
	private float balance;

	public Account(UUID id, int accountNumber, float balance) {
		this.id = id;
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
}
