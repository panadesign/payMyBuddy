package com.payMyBuddy.model;

import com.sun.istack.NotNull;
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
import java.util.Currency;
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
	
	
	public Transaction(UserAccount creditorAccount, UserAccount debtorAccount, double amount, String currency, String description) {
		this.creditor = creditorAccount;
		this.debtor = debtorAccount;
		this.amount = amount;
		this.currency = currency;
		this.description = description;
		this.creationDate = LocalDate.now();
	}
}
