package com.PayMyBuddy.model;

public class Transaction {

	private float amount;
	private String currency;

	public Transaction(float amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
