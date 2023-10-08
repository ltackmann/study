package jmx.metrics;

import java.util.List;
import java.util.Map;

import javax.management.MXBean;

import jmx.metrics.stat.GuiStat;

@MXBean
public interface GuiMetrics {
	List<String> getMostVisitedPages();
	
	List<GuiStat> getPagesWithMostServicesCalls();
	
	List<GuiStat> getSlowestPages();
}
