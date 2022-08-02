package com.payMyBuddy.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table
@NoArgsConstructor
public class Transaction {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@Type(type = "uuid-char")
	private UUID id;
	private double amount;
	private String currency;
	private String description;
	private LocalDate creationDate;
	
	@ManyToOne
	@JoinColumn
	private UserAccount debtor;
	
	@ManyToOne
	@JoinColumn
	private UserAccount creditor;
	
	public Transaction(UserAccount debtorAccount, UserAccount creditorAccount, double amount, String currency, String description) {
		this.debtor = debtorAccount;
		this.creditor = creditorAccount;
		this.amount = amount;
		this.currency = currency;
		this.description = description;
		this.creationDate = LocalDate.now();
	}

	public Transaction(UUID id, double amount, String description) {
		this.id = id;
		this.amount = amount;
		this.description = description;
	}

	public Transaction(UUID id, double amount, String description, String currency, UserAccount debtor, UserAccount creditor) {
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.currency = currency;
		this.creationDate = LocalDate.now();
		this.debtor = debtor;
		this.creditor = creditor;
	}

	public Transaction(UUID id, double amount, String description, String currency) {
		this.id = id;
		this.amount = amount;
		this.description = description;
		this.currency = currency;
	}
}
