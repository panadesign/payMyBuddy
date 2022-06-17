package com.payMyBuddy.model;

import lombok.Data;

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
public class Transaction {

	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id;
	private float amount;
	private Currency currency;
	private String description;
	private LocalDate creationDate;

	@ManyToOne
	@JoinColumn
	private Person debtor;


	@ManyToOne
	@JoinColumn
	private Person creditor;
}
