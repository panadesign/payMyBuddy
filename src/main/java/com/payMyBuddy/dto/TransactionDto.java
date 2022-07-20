package com.payMyBuddy.dto;

import com.payMyBuddy.model.Transaction;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class TransactionDto {

	private String firstname;
	private String description;
	private double amount;

	@Autowired
	private UserAccountDto userAccountDto;

	public TransactionDto(Transaction transaction, UserAccountDto userAccountDto) {
		this.firstname = userAccountDto.getFirstname();
		this.description = transaction.getDescription();
		this.amount = transaction.getAmount();
	}

	public TransactionDto() {

	}

}
