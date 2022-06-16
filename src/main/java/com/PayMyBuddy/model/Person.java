package com.PayMyBuddy.model;

import lombok.Data;

import javax.persistence.CascadeType;
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
@Entity
@Table
public class Person {

	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id;

	@Column
	private String email;

	@Column
	private String firstname;

	@Column
	private String lastname;

	@Column
	private String password;

	@OneToOne(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER
	)
	@JoinColumn
	private Account account;

}
