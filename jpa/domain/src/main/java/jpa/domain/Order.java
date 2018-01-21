package jpa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * An order and its items 
 */
@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {
	private static final long serialVersionUID = 448094322022735095L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATION_DATE", nullable = false)
	private Date dateCreated;

	@Enumerated
	@Column(name = "ORDER_STATUS", nullable = false)
	private OrderStatus status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, updatable = false)
	private Employee employee;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Customer customer;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = { CascadeType.ALL })
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Employee getEmploye() {
		return employee;
	}

	public void setEmploye(Employee employe) {
		this.employee = employe;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public long getId() {
		return id;
	}

	public Date getDateCreated() {
		return dateCreated;
	}
}