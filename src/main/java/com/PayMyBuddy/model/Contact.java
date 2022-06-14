package com.PayMyBuddy.model;

import java.util.List;

public class Contact {

	private List<Person> friendList;
	private String firstName;
	private String lastName;
	private String email;

	public Contact(List<Person> friendList, String firstName, String lastName, String email) {
		this.friendList = friendList;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public List<Person> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<Person> friendList) {
		this.friendList = friendList;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
