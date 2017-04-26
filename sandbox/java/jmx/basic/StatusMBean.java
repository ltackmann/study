package jmx.basic;

public interface StatusMBean {
	public StatusEnum getStatus();

	public void setStatus(final StatusEnum status);

	public enum StatusEnum {
		SUCCESSFUL, FAILURE, UNSPECIFIED
	};
}
