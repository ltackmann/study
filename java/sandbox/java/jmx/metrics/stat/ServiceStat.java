package jmx.metrics.stat;

import java.beans.ConstructorProperties;
import java.util.List;

public class ServiceStat {
	private final String serviceName;
	private final List<String> calledFrom;
	//private final List<String> userIds = new LinkedList<>();
	private final long serviceCalls;
	//private long minExecutionTime = Long.MAX_VALUE;
	//private long maxExecutionTime = Long.MIN_VALUE;
	private final long averageExecutionTime;
	
	@ConstructorProperties({"serviceName", "calledFrom", "serviceCalls", "averageExecutionTime"}) 
	public ServiceStat(String serviceName, List<String> calledFrom, long serviceCalls, long averageExecutionTime) {
		this.serviceName = serviceName;
		this.calledFrom = calledFrom;
		this.serviceCalls = serviceCalls;
		this.averageExecutionTime = averageExecutionTime;
	}

	public String getServiceName() {
		return serviceName;
	}

	public List<String> getCalledFrom() {
		return calledFrom;
	}

	public long getServiceCalls() {
		return serviceCalls;
	}

	public long getAverageExecutionTime() {
		return averageExecutionTime;
	}
}
