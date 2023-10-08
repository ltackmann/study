package jmx.metrics.beans;

import java.util.Arrays;
import java.util.List;

import jmx.metrics.SystemMetrics;

public class SystemMetricsImpl implements SystemMetrics {
	@Override
	public List<String> getActiveUsers() {
		return Arrays.asList("Hanne", "Lise", "Bent");
	}
}