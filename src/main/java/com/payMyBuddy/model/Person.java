package com.payMyBuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
//@DynamicUpdate
@EqualsAndHashCode
public class Person {

	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column
	private String firstname;

	@Column
	private String lastname;

	@Column
	private String password;

	@Column
	private AccountStatus status;

	@OneToOne(
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER
	)
	@JoinColumn
	private Account account;

	@OneToMany(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			}
	)
	@JoinTable(
			name = "friendslist",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "friend_id")
	)
	private List<Person> friendsList = new ArrayList<>();

	public Person(String email, String firstname, String lastname, String password) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}

	public Person(String email, String firstname, String lastname, String password, List<Person> friendsList) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.friendsList = friendsList;
	}


}
