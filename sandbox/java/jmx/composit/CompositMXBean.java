package jmx.composit;

import java.util.List;

public interface CompositMXBean {
	StatusEnum getStatus();

	void setStatus(final StatusEnum status);
	
	List<String> getCalls();

	public enum StatusEnum {
		SUCCESSFUL, FAILURE, UNSPECIFIED
	};
}
