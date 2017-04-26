package jmx.basic;

public class Basic implements BasicMBean {
	private StatusEnum statusEnum = StatusEnum.UNSPECIFIED;

	public Basic() {
	}

	public StatusEnum getStatus() {
		return this.statusEnum;
	}

	public void setStatus(final StatusEnum status) {
		this.statusEnum = status;
	}
}
