package org.randompage.samples.hibernate.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Result {
	private String memberMsisdn;
	private Date date;
	private BigDecimal request;
	private String action;

	public String getAction() {
		return action;
	}

	public Date getDate() {
		return date;
	}

	public String getMemberMsisdn() {
		return memberMsisdn;
	}

	public BigDecimal getRequest() {
		return request;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setMemberMsisdn(String memberMsisdn) {
		this.memberMsisdn = memberMsisdn;
	}

	public void setRequest(BigDecimal request) {
		this.request = request;
	}
}
