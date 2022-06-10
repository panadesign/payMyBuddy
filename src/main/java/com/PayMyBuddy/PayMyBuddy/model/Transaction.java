package com.PayMyBuddy.PayMyBuddy.model;

import java.util.Currency;

public class Transaction {

	private float amount;
	private Currency currency;
	private String description;

	public Transaction(float amount, Currency currency, String description) {
		this.amount = amount;
		this.currency = currency;
		this.description = description;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
