package jmx.metrics.beans;

import java.util.Arrays;
import java.util.List;

import jmx.metrics.GuiMetrics;
import jmx.metrics.stat.GuiStat;

public class GuiMetricsImpl implements GuiMetrics {
	@Override
	public List<String> getMostVisitedPages() {
		return Arrays.asList("LoginPage", "PersonPage");
	}

	@Override
	public List<GuiStat> getPagesWithMostServicesCalls() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GuiStat> getSlowestPages() {
		// TODO Auto-generated method stub
		return null;
	}
}