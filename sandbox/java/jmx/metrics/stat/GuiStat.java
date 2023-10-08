package jmx.metrics.stat;

import java.beans.ConstructorProperties;
import java.util.List;

public class GuiStat {
	private final String pageName;
	private final List<String> servicesUsed;
	private final long pageLoads;
	private final long averageLoadTime;
	
	@ConstructorProperties({"pageName", "servicesUsed", "pageLoads", "averageLoadTime"}) 
	public GuiStat(String pageName, List<String> servicesUsed, long pageLoads, long averageLoadTime) {
		this.pageName = pageName;
		this.servicesUsed = servicesUsed;
		this.pageLoads = pageLoads;
		this.averageLoadTime = averageLoadTime;
	}

	public String getPageName() {
		return pageName;
	}

	public List<String> getServicesUsed() {
		return servicesUsed;
	}

	public Long getPageLoads() {
		return pageLoads;
	}

	public long getAverageLoadTime() {
		return averageLoadTime;
	}
}
