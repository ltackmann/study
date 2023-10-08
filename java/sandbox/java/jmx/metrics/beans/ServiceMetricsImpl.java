package jmx.metrics.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import jmx.metrics.ServiceMetrics;
import jmx.metrics.stat.ServiceStat;

public class ServiceMetricsImpl implements ServiceMetrics {
	@Override
	public Map<String, Long> getSlowestServices() {
		return MetricsRegistryImpl.serviceStats.values().stream().sorted((s1, s2) -> {
			return Long.compare(s1.getAverageExecutionTime(), s2.getAverageExecutionTime());
		}).reduce(new HashMap<String,Long>(), (map,st) -> {
			map.put(st.getServiceName(), st.getAverageExecutionTime());
			return map;
		}, (map,st) -> st);
	}

	@Override
	public List<ServiceStat> getMostCalledServices() {
		return MetricsRegistryImpl.serviceStats.values().stream().sorted((s1, s2) -> {
			return Long.compare(s1.getServiceCalls(), s2.getServiceCalls());
		}).collect(Collectors.toList());
	}

	@Override
	public long getServiceCallsPerMinute() {
		return ThreadLocalRandom.current().nextLong(100);
	}
}