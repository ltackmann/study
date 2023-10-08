package jpa.domain;

import javax.persistence.*;

/**
 * Track system logins
 */
@Entity
@Table(name = "LOGIN_LOG")
@AttributeOverride(column = @Column(name="LOGIN_DATE", nullable = false), name = "date")
@AssociationOverride(joinColumns = { @JoinColumn(name="USERS_ID", nullable = false) }, name = "user")
public class LoginLog extends Log {
	private static final long serialVersionUID = 527360489055013915L;
}
