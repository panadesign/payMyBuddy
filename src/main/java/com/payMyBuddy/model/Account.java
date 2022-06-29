package com.payMyBuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Account {

	@Id
	@GeneratedValue(generator = "UUID")
	private UUID account_id;

	@Column
	private float balance;

	@OneToOne(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER
	)
	@JoinColumn
	private AppUser appUser;

	@OneToMany(
			cascade = CascadeType.PERSIST,
			fetch = FetchType.EAGER
	)
	@Column(name = "account_id")
	List<Transaction> transactions = new ArrayList<>();
}
