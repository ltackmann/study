package org.randompage.bookmarking.rest.testUtils;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response.Status;

/**
 * Data and utility functions handy for testing
 * 
 * @author Lars Tackmann
 */
public class TestUtils {
	
	private static Map<Integer, Status> statusMap;
	static {
		statusMap = new HashMap<Integer, Status>();
		for (Status status : Status.values())
			statusMap.put(status.getStatusCode(), status);
	}

	public static Status getStatus(int statusCode) {
		if (!statusMap.containsKey(statusCode)) {
			throw new IllegalArgumentException("Unknown HTTP status code: "
					+ statusCode);
		}
		return statusMap.get(statusCode);
	}
}
