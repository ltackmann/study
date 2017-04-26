package jmx.basic;

public interface BasicMXBean {
	public StatusEnum getStatus();

	public void setStatus(final StatusEnum status);

	public enum StatusEnum {
		SUCCESSFUL, FAILURE, UNSPECIFIED
	};
}
