package jmx.metrics;

import javax.management.MXBean;

@MXBean
public interface MetricsRegistry {
	ServiceMetrics getServiceMetrics();
	
	GuiMetrics getGuiMetrics();
	
	DatabaseMetrics getDatabaseMetrics();
	
	SystemMetrics getSystemMetrics();
	
	int getCount();
	
	void setCount(int count);
}