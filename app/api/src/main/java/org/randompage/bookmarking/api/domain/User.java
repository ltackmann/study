package org.randompage.bookmarking.api.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.codec.digest.DigestUtils;

@Entity
@Table(name = "users")
public class User extends IDEntity {
	private static final long serialVersionUID = -4304124449410941989L;

	// TODO validation
	// @Length(min = 5, max = 100)
	// @Pattern(regex = "^\\w*$", message = "not a valid password")
	@Column(name = "password_md5", nullable = false)
	private String password;

	// @Length(max = 100)
	@Column(name = "full_name", nullable = false)
	private String name;

	// @Length(min = 6, max = 100)
	// @Pattern(regex = ".+@.+\\.[a-z]+", message =
	// "invalid email address")
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "last_login")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;

	@Column(name = "creation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_bookmarks", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "bookmark_id"))
	private Set<Bookmark> bookmarks = new HashSet<Bookmark>();

	public User() {
	}

	public User(String name, Role role, String email, String password) {
		this();
		setName(name);
		setRole(role);
		setEmail(email);
		setPassword(password);
	}

	public Set<Bookmark> getBookmarks() {
		return bookmarks;
	}

	public String getEmail() {
		return email;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public Role getRole() {
		return role;
	}

	@PrePersist
	void prePersist() {
		if (creationDate == null) {
			creationDate = new Date();
		}
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = DigestUtils.md5Hex(password);
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
