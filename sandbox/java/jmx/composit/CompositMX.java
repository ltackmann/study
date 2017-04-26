package jmx.composit;

import java.util.Arrays;
import java.util.List;

public class CompositMX implements CompositMXBean {
	private StatusEnum statusEnum = StatusEnum.UNSPECIFIED;

	public StatusEnum getStatus() {
		return this.statusEnum;
	}

	// TODO operations
	public void setStatus(final StatusEnum status) {
		this.statusEnum = status;
	}

	@Override
	public List<String> getCalls() {
		return Arrays.asList("a", "b", "c");
	}
}