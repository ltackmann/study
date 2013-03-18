package org.randompage.samples.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ADDRESSES")
public class Address implements Serializable {
	private static final long serialVersionUID = 5992202784266467771L;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "COUNTRIES_ID", nullable = false)
	private Country country;

	@Id
	@GeneratedValue
	private long id;

	@Column(name = "STREET_NAME", nullable = false)
	private String streetName;

	@Column(name = "STREET_NUMBER", nullable = false)
	private String streetNo;

	@Column(nullable = false)
	private String city;

	private String zip;

    @Column(name = "DOOR_NUMBER")
	private String doorNo;

    @Column(name = "FLOOR_NUMBER")
	private String floorNo;

    public Address(String streetName, String streetNo, String city, Country country) {
        this();
        this.streetName = streetName;
        this.streetNo = streetNo;
        this.city = city;
        this.country = country;
    }

    public Address() {
    }

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Address other = (Address) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	public Country getCountry() {
		return country;
	}

	/**
	 * @return the doorNo
	 */
	public String getDoorNo() {
		return doorNo;
	}

	/**
	 * @return the floorNo
	 */
	public String getFloorNo() {
		return floorNo;
	}

	public long getId() {
		return id;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @return the streetNo
	 */
	public String getStreetNo() {
		return streetNo;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * @param doorNo
	 *            the doorNo to set
	 */
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	/**
	 * @param floorNo
	 *            the floorNo to set
	 */
	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	/**
	 * @param streetName
	 *            the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @param streetNo
	 *            the streetNo to set
	 */
	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	/**
	 * @param zip
	 *            the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
}