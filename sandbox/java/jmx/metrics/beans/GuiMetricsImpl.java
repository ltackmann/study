package jmx.metrics.beans;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jmx.metrics.GuiMetrics;

public class GuiMetricsImpl implements GuiMetrics {
	@Override
	public Map<String, List<String>> getServicesPerPage() {
		return new HashMap<String,List<String>>() {{
			put("PersonPage", Arrays.asList("AQuery", "BQuery"));
			put("SettingsPage", Arrays.asList("DQuery", "CQuery"));
		}};
	}

	@Override
	public List<String> getMostVisitedPages() {
		return Arrays.asList("LoginPage", "PersonPage");
	}
}