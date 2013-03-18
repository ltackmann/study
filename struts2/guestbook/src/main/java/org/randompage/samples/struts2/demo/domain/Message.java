package org.randompage.samples.struts2.demo.domain;

import java.util.Date;

public class Message {
	private String guest;
	private String message;
	private Date when;

	public String getGuest() {
		return guest;
	}

	public String getMessage() {
		return message;
	}

	public Date getWhen() {
		return when;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setWhen(Date when) {
		this.when = when;
	}

}
