package jmx.metrics;

import java.util.List;
import java.util.Map;

import javax.management.MXBean;

@MXBean
public interface GuiMetrics {
	List<String> getMostVisitedPages();
	
	Map<String,List<String>> getServicesPerPage();
}
