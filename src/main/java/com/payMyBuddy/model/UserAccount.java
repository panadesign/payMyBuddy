package com.payMyBuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table
public class UserAccount {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@Type(type = "uuid-char")
	private UUID id;

	@Column(nullable = false, unique = true)
	@NonNull
	private String email;

	@Column
	@NonNull
	private String firstname;

	@Column
	@NonNull
	private String lastname;

	@Column
	@NonNull
	private String password;

	@Column
	private String iban;

	@Column
	private AccountStatus status = AccountStatus.ACTIVE;

	@OneToOne(
			cascade = CascadeType.ALL
	)
	private Account account;

	@ManyToMany(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			}
	)
	private List<UserAccount> contactList = new ArrayList<>();



	public UserAccount(String email, String firstname, String lastname, String password) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}

	public UserAccount(UUID id, String email, String firstname, String lastname, String password, String iban, AccountStatus status, Account account) {
		this.id = id;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.iban = iban;
		this.status = status;
		this.account = account;
	}

	public String getName() {
		return this.firstname + " " + this.lastname;
	}
	
}
