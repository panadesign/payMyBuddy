package com.payMyBuddy.dto;

import com.payMyBuddy.model.Transaction;
import lombok.Value;

@Value
public class TransactionDto {

	String debtorName;
	String creditorName;
	String description;
	double amount;

	public TransactionDto(Transaction transaction) {
		this.debtorName = transaction.getDebtor().getName();
		this.creditorName = transaction.getCreditor().getName();
		this.description = transaction.getDescription();
		this.amount = transaction.getAmount();
	}
}
