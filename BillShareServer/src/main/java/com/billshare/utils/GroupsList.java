package com.billshare.utils;

import java.util.List;

public class GroupsList {
	private ResponseStatus responseStatus;
	private List<GroupInfo> groupInfo;

	
	public List<GroupInfo> getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(List<GroupInfo> groupInfo) {
		this.groupInfo = groupInfo;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

}
