package com.PayMyBuddy.model;

import java.util.List;

public class FriendList {

	List<User> friendList;

	public FriendList(List<User> friendList) {
		this.friendList = friendList;
	}

	public List<User> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<User> friendList) {
		this.friendList = friendList;
	}
}
