package com.billshare.utils;

/*
status

P=pending
R=Reject
A=Accepted
 */

public enum Status {
	PENDING("P"), ACCEPTED("A"), REJECT("R");
	private String friendStatus;

	Status(String friendStatus) {
		this.friendStatus = friendStatus;
	}

	public String getStatus() {
		return friendStatus;
	}

}
