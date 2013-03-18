package org.randompage.bookmarking.api.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "taggable_type", discriminatorType = DiscriminatorType.STRING)
public class Taggable extends IDEntity {
	private static final long serialVersionUID = -8703488632923546349L;

	@Column(name = "taggable_type", insertable = false, updatable = false)
	private String taggableType;

	public String getTaggableType() {
		return taggableType;
	}
}
