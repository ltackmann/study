package co.tackmann.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Represents part of a order and its configuration
 * 
 * @author Lars Tackmann
 * 
 */
@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = -2080523684247744032L;

	@EmbeddedId
	private OrderItemPK pk;

	@Column(name = "UNIT_PRICE", nullable = false)
	private BigDecimal unitPrice;

	@Column(nullable = false)
	private int quantity;

	private BigDecimal discount;

	public OrderItem() {
		super();
		if (pk == null)
			pk = new OrderItemPK();
	}

	// map to same columns as used in composite primary key (pk)
	// set insertable/updatable to false so only updates/inserts
	// are handled via the defenition in the primary key
	@ManyToOne
	@JoinColumn(name = "PRODUCTS_ID", insertable = false, updatable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "ORDERS_ID", insertable = false, updatable = false)
	private Order order;

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		this.pk.setProductId(product.getId());
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
		this.pk.setOrderId(order.getId());
	}
}