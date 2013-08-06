package co.tackmann.jpa.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@SuppressWarnings("serial")
public class OrderItemPK implements java.io.Serializable {
	@Column(name = "ORDERS_ID", nullable = false)
	private long orderId;
	@Column(name = "PRODUCTS_ID", nullable = false)
	private long productId;

	public OrderItemPK() {
		super();
	}

	public OrderItemPK(long orderId, long productId) {
		this();
		this.orderId = orderId;
		this.productId = productId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final OrderItemPK other = (OrderItemPK) obj;
		if (orderId != other.orderId)
			return false;
		if (productId != other.productId)
			return false;
		return true;
	}

	public long getOrderId() {
		return orderId;
	}

	public long getProductId() {
		return productId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (orderId ^ (orderId >>> 32));
		result = prime * result + (int) (productId ^ (productId >>> 32));
		return result;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

}