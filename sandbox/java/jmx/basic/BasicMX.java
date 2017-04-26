package jmx.basic;

public class BasicMX implements BasicMXBean {
	private StatusEnum statusEnum = StatusEnum.UNSPECIFIED;

	public StatusEnum getStatus() {
		return this.statusEnum;
	}

	public void setStatus(final StatusEnum status) {
		this.statusEnum = status;
	}
}