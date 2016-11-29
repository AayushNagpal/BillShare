package com.billshare.dto;

import java.math.BigDecimal;
import java.util.List;

import com.billshare.domain.Friend;
import com.billshare.utils.ResponseStatus;

public class GroupDTO {
	private Integer id;
	private Integer adminId;
	private String name;
	private BigDecimal amount;
	private List<Friend> friends;
	private String image;
	private ResponseStatus responseStatus;
	private BigDecimal amountLimit;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getAmountLimit() {
		return amountLimit;
	}

	public void setAmountLimit(BigDecimal amountLimit) {
		this.amountLimit = amountLimit;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public List<Friend> getFriendsIds() {
		return friends;
	}

	public void setFriendsIds(List<Friend> friends) {
		this.friends = friends;
	}

}
