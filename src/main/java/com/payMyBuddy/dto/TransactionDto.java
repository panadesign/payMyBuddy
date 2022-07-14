package com.payMyBuddy.dto;

import com.payMyBuddy.model.Transaction;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class TransactionDto {

	private String email;
	private String description;
	private double amount;

	@Autowired
	private UserAccountDto userAccountDto;

	TransactionDto(Transaction transaction, UserAccountDto userAccountDto) {
		this.email = userAccountDto.getEmail();
		this.description = transaction.getDescription();
		this.amount = transaction.getAmount();
	}

}
