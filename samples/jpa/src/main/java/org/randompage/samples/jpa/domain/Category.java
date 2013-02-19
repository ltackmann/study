package org.randompage.samples.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Product categories
 * 
 * @author Lars Tackmann
 */
@Entity
@Table(name = "CATEGORIES")
public class Category implements Serializable {
	private static final long serialVersionUID = -3683799465703834520L;

	@Id
	@GeneratedValue
	private long id;

	@Lob
    @Basic(optional = false)
	private String description;

	@Column(name = "NAME", nullable = false, length = 20)
	private String categoryName;

	public String getCategoryName() {
		return categoryName;
	}

	public String getDescription() {
		return description;
	}

	public long getId() {
		return id;
	}

	public Object getIdentity() {
		return getId();
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}