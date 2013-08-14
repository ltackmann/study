package co.tackmann.jpa.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("0")
public class Customer extends User {
	private static final long serialVersionUID = 228391830747000072L;

	@OneToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "ADDRESSES_ID", nullable = false)
	private Address address;

	@OneToMany(mappedBy = "customer", cascade = { CascadeType.REMOVE })
	private Set<Order> orders = new HashSet<Order>();

    public Customer() {
    }

    public Customer(Address address) {
        this.address = address;
    }

    public Customer(String email, String username, String password, String name, Address address) {
        super(email, username, password, name);
        this.address = address;
    }

    public Address getAddress() {
		return address;
	}

	public Set<Order> getOrders() {
		return new HashSet<Order>(orders);
	}

	public void setAddress(Address billingAddress) {
		this.address = billingAddress;
	}
}