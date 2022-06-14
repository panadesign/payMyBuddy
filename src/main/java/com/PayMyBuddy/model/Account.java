package com.PayMyBuddy.model;

import java.util.UUID;

public class Account {

	private UUID id;
	private float balance;

	public Account(UUID id, float balance) {
		this.id = id;
		this.balance = balance;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
}
