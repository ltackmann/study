package org.randompage.bookmarking.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;

/**
 * named collection of tags
 * 
 * @author Lars Tackmann
 * 
 */
@Entity
@Table(name = "bundles", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"NAME", "USER_ID" }) })
public class Bundle extends IDEntity {
	private static final long serialVersionUID = 8033267129899533295L;
	@ManyToOne
	@JoinColumn(nullable = false)
	private User user;

	@Column(nullable = false)
	private String name;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
