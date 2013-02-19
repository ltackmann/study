package org.randompage.bookmarking.ws;

import javax.annotation.Resource;

import org.randompage.bookmarking.api.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

// FIXME write unit test
/**
 * Service EndPoint for handling human resource requests
 * 
 * @author Lars Tackmann
 */
@Endpoint
public class HumanResourceEndpoint {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private UserManager userManager;
	

/*
	@PayloadRoot(localPart = "HolidayRequest", namespace = "http://randompage.org/hr/schemas")
	public HolidayResponse reserveHoliday(HolidayRequest request)
			throws HumanResourceException {
		
		return null;
	}
*/

	@Resource
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}