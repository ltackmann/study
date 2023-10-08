package jpa.domain;

import javax.persistence.*;

/**
 * Used to track auditable actions
 */
@Entity
@Table(name="AUDIT_LOG")
@AttributeOverride(column = @Column(name="AUDIT_TIME", nullable = false), name = "date")
@AssociationOverride(joinColumns = { @JoinColumn(name = "AUDIT_USERS_ID", nullable = false) }, name = "user")
public class AuditLog extends Log {
	private static final long serialVersionUID = 3903944779305771579L;

	@Column(nullable = false)
	private String action;

	@Column(nullable = false)
	private String target;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}
