package jmx.metrics;

import java.util.List;

import javax.management.MXBean;

@MXBean
public interface DatabaseMetrics {
	List<String> getMostQueriedTables();
	
	List<String> getMostCommittedTables();
}
