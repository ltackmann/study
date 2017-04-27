package jmx.metrics.beans;

import java.util.Arrays;
import java.util.List;

import jmx.metrics.DatabaseMetrics;

public class DatabaseMetricsImpl implements DatabaseMetrics {
	@Override
	public List<String> getMostQueriedTables() {
		return Arrays.asList("OiAccount", "Person");
	}

	@Override
	public List<String> getMostCommittedTables() {
		return Arrays.asList("LifePolicy", "Person");
	}
}