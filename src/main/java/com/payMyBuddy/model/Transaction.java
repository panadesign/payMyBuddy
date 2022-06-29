package com.payMyBuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Transaction {

	@Id
	@GeneratedValue(generator = "UUID")
	private UUID transaction_id;
	private float amount;
	private Currency currency;
	private String description;
	private LocalDate creationDate;

	@ManyToOne
	@JoinColumn
	private AppUser debtor;


	@ManyToOne
	@JoinColumn
	private AppUser creditor;
//
//	@ManyToOne(
//			cascade = CascadeType.PERSIST,
//	fetch = FetchType.EAGER
//	)
//	@Column(name = "transaction_id")
//	private Account account;
}
