package org.randompage.samples.backend.domain;

import java.util.Date;

/**
 * @author Lars Tackmann
 */
// TODO check that from is before to
public class Holiday {
	private Date from;
	
	private Date to;

	public Holiday() {
		super();
	}
	
	public Holiday(Date from, Date to) {
		this();
		this.from = from;
		this.to = to;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}
}
