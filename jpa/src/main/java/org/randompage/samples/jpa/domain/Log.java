package org.randompage.samples.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class Log implements Serializable {
	private static final long serialVersionUID = -7453772910953157988L;

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@PrePersist
	protected void prePersist() {
		if (date == null)
			date = new Date();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public long getId() {
		return id;
	}
}
