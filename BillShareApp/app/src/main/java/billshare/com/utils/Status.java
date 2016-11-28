package billshare.com.utils;

/*
status

P=pending
R=Reject
A=Accepted
 */

import com.fasterxml.jackson.annotation.JsonIgnore;

public enum Status {
	PENDING("P"), ACCEPTED("A"), REJECT("R");
	@JsonIgnore
	private String friendStatus;

	Status(String friendStatus) {
		this.friendStatus = friendStatus;
	}

	public String getStatus() {
		return friendStatus;
	}

}
