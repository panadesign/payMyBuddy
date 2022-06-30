package com.payMyBuddy.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
public class AppUser {
	
	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id;

	@Column(nullable = false, unique = true)
	@NotNull
	private String email;

	@Column
	@NotNull
	private String firstname;

	@Column
	@NotNull
	private String lastname;

	@Column
	@NotNull
	private String password;

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
	private List<AppUser> friendsList = new ArrayList<>();


	public AppUser(String email, String firstname, String lastname, String password) {
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.account = new Account();
	}

}
