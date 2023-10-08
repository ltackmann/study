package jmx.metrics;

import java.util.List;

import javax.management.MXBean;

@MXBean
public interface SystemMetrics {
	List<String> getActiveUsers();
}
