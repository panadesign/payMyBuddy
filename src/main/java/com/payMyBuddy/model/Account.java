package com.payMyBuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Account {
	
	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id;
	
	@Column
	private float balance = 0;

//	@OneToMany(
//			cascade = CascadeType.PERSIST,
//			fetch = FetchType.EAGER
//	)
//	@Column(name = "account_id")
//	List<Transaction> transactions = new ArrayList<>();
}
