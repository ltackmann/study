package org.randompage.bookmarking.api.domain;

import java.lang.reflect.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Generic functionality for objects with generated primary key
 * 
 * @author Lars Tackmann
 * 
 */
@MappedSuperclass
@SuppressWarnings("serial")
abstract class IDEntity implements IEntity<Long> {
	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

    public void setId(Long id) {
        this.id = id;
	}


	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final IDEntity other = (IDEntity) obj;
		if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String str = "\nClass: " + getClass().getSimpleName() + "\n";
		str += ("Id: " + getId() + "\n");
		for (Field field : getClass().getDeclaredFields()) {
			field.setAccessible(true);
			str += field.getName();
			try {
				Object obj = field.get(this);
				if (obj instanceof IDEntity)
					str += " id: " + ((IDEntity) obj).getId();
				else
					str += (obj == null ? " null" : ": " + obj.toString());
			} catch (Exception e) {
				// nothing
			}
			str += "\n";
		}
		return str;
	}
}
