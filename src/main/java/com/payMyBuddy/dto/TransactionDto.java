package com.payMyBuddy.dto;

import com.payMyBuddy.model.Transaction;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;

@Value
public class TransactionDto {

	String firstname;
	String description;
	double amount;

	public TransactionDto(Transaction transaction) {
		this.firstname = transaction.getCreditor().getName();
		this.description = transaction.getDescription();
		this.amount = transaction.getAmount();
	}
}
