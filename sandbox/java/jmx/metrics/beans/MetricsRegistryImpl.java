package jmx.metrics.beans;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import jmx.metrics.DatabaseMetrics;
import jmx.metrics.GuiMetrics;
import jmx.metrics.MetricsRegistry;
import jmx.metrics.ServiceMetrics;
import jmx.metrics.SystemMetrics;

public class MetricsRegistryImpl implements MetricsRegistry {
	static Map<String, ServiceStat> serviceStats = new HashMap<>();
	static {
		serviceStats.put("AQuery", new ServiceStat("AQuery", Arrays.asList("LoginPage", "PersonPage"), 23, 100L));
		serviceStats.put("BQuery", new ServiceStat("BQuery", Arrays.asList("PersonPage", "SettingsPage"), 12, 234L));
		serviceStats.put("CQuery", new ServiceStat("CQuery", Arrays.asList("PersonPage"), 4, 123L));
		serviceStats.put("DQuery", new ServiceStat("DQuery", Arrays.asList("SettingsPage"), 17, 88L));
		serviceStats.put("EQuery", new ServiceStat("EQuery", Arrays.asList("AccountPage", "PersonPage"), 9, 400L));
	}
	private int count = 10;

	@Override
	public ServiceMetrics getServiceMetrics() {
		return new ServiceMetricsImpl();
	}

	@Override
	public GuiMetrics getGuiMetrics() {
		return new GuiMetricsImpl();
	}

	@Override
	public DatabaseMetrics getDatabaseMetrics() {
		return new DatabaseMetricsImpl();
	}

	@Override
	public SystemMetrics getSystemMetrics() {
		return new SystemMetricsImpl();
	}

	@Override
	public int getCount() {
		return count;
	}

	@Override
	public void setCount(int count) {
		this.count = count;
	}
}
