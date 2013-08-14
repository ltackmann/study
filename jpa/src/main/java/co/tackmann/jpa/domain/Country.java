package co.tackmann.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Supported country names and their ISO 3166 2-letter code
 * 
 * @author Lars Tackmann
 */
@Entity
@Table(name = "COUNTRIES")
public class Country implements Serializable {
	private static final long serialVersionUID = -6105808380408786255L;

	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true, nullable = false, length = 2)
	private String code;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	public Country(String name, String code) {
		this.name = name;
		this.code = code;
	}
	
	public Country() {
		
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Country other = (Country) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String getCode() {
		return code;
	}

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
