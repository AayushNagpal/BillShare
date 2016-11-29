package com.billshare.domain;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(name = "mobile_no")
	private String mobileNo;
	@Column(name = "email_id")
	private String emailId;
	private String currency;
	@Column(name = "time_zone")
	private String timeZone;
	@Column(name = "language_code")
	private String langugeCode;
	@Column(name = "device_id")
	private String deviceId;
	private String password;

	private Blob _profilePic;

	/*
	 * public String getProfilePic1() { return profilePic1; }
	 * 
	 * public void setProfilePic1(String profilePic1) { this.profilePic1 =
	 * profilePic1; }
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getLangugeCode() {
		return langugeCode;
	}

	public void setLangugeCode(String langugeCode) {
		this.langugeCode = langugeCode;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Blob get_profilePic() {
		return _profilePic;
	}

	public void set_profilePic(Blob _profilePic) {
		this._profilePic = _profilePic;
	}

}
