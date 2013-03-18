package org.randompage.samples.seam.wiki.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Track logins
 * 
 * @author Lars Tackmann
 * 
 */
@Entity
@Table(name = "LOGIN_LOG")
public class LoginLog implements Serializable {
	private static final long serialVersionUID = 527360489055013915L;
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;
	
	@Column(nullable = false)
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

	public long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}
}
