package org.randompage.samples.seam.wiki.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.validator.Length;
import org.hibernate.validator.Pattern;
import org.jboss.seam.annotations.Name;

@Entity
@Name("user")
@Table(name = "USERS")
public class User implements Serializable {
	private static final long serialVersionUID = 5885457903763444083L;

	@Id
	@GeneratedValue
	private long id;

	@Length(min = 5, max = 15)
	@Pattern(regex = "^\\w*$", message = "not a valid username")
	@Column(nullable = false, unique = true)
	private String username;

	@Length(min = 5, max = 15)
	@Column(nullable = false)
	private String password;

	@Length(max = 100)
	@Column(nullable = false)
	private String name;

	@Length(min = 6, max = 100)
	@Pattern(regex = "(\\w+)@(\\w+\\.)(\\w+)(\\.\\w+)*", message = "invalid email address")
	@Column(nullable = false)
	private String email;

	public String getEmail() {
		return email;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = DigestUtils.md5Hex(password);
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
