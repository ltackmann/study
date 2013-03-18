package org.randompage.samples.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Product manufacturer
 *
 * @author Lars Tackmann
 */
@Entity
@Table(name = "SUPPLIERS")
public class Supplier implements Serializable {
    private static final long serialVersionUID = 4893414104626418896L;

    @Id
    @GeneratedValue
    private long id;

    @Transient
    private String sessionId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "CONTACT_PERSON_NAME", nullable = false, length = 50)
    private String contactPerson;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ADDRESSES_ID", nullable = false)
    private Address address;

    @Column
    private String url;

    public Supplier() {
    }

    public Supplier(String name, String contactPerson, Address address) {
        this();
        this.name = name;
        this.contactPerson = contactPerson;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
