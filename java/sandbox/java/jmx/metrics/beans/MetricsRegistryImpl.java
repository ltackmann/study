package jmx.metrics.beans;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import jmx.metrics.DatabaseMetrics;
import jmx.metrics.GuiMetrics;
import jmx.metrics.MetricsRegistry;
import jmx.metrics.ServiceMetrics;
import jmx.metrics.SystemMetrics;
import jmx.metrics.stat.ServiceStat;

public class MetricsRegistryImpl implements MetricsRegistry {
	static Map<String, ServiceStat> serviceStats = new HashMap<>();
	static {
		serviceStats.put("AQuery", new ServiceStat("AQuery", Arrays.asList("LoginPage", "PersonPage"), 23L, 100L));
		serviceStats.put("BQuery", new ServiceStat("BQuery", Arrays.asList("PersonPage", "SettingsPage"), 12L, 234L));
		serviceStats.put("CQuery", new ServiceStat("CQuery", Arrays.asList("PersonPage"), 4L, 123L));
		serviceStats.put("DQuery", new ServiceStat("DQuery", Arrays.asList("SettingsPage"), 17L, 88L));
		serviceStats.put("EQuery", new ServiceStat("EQuery", Arrays.asList("AccountPage", "PersonPage"), 9L, 400L));
	}
	private int count = 10;
	private final DatabaseMetrics databaseMetrics;
	private final GuiMetrics guiMetrics;
	private final ServiceMetrics serviceMetrics;
	private final SystemMetrics systemMetrics;

	public MetricsRegistryImpl(DatabaseMetrics databaseMetrics, GuiMetrics guiMetrics, ServiceMetrics serviceMetrics, SystemMetrics systemMetrics) {
		this.databaseMetrics =  databaseMetrics;
		this.guiMetrics = guiMetrics;
		this.serviceMetrics = serviceMetrics;
		this.systemMetrics = systemMetrics;
	}

	@Override
	public DatabaseMetrics getDatabaseMetrics() {
		return databaseMetrics;
	}

	@Override
	public GuiMetrics getGuiMetrics() {
		return guiMetrics;
	}


	@Override
	public ServiceMetrics getServiceMetrics() {
		return serviceMetrics;
	}

	@Override
	public SystemMetrics getSystemMetrics() {
		return systemMetrics;
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
