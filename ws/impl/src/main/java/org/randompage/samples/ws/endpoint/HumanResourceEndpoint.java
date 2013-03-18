package org.randompage.samples.ws.endpoint;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.randompage.samples.backend.domain.Holiday;
import org.randompage.samples.ws.api.HolidayList;
import org.randompage.samples.ws.api.HolidayRequest;
import org.randompage.samples.ws.api.HolidayResponse;
import org.randompage.samples.ws.api.HolidayType;
import org.randompage.samples.ws.backend.HolidayManager;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

// FIXME write unit test
@Endpoint
public class HumanResourceEndpoint {
	protected final Log logger = LogFactory.getLog(getClass());

	private HolidayManager holidayManager;

	@PayloadRoot(localPart = "HolidayRequest", namespace = "http://randompage.org/hr/schemas")
	public HolidayResponse reserveHoliday(HolidayRequest request) {
		String username = request.getEmployee().getUserName();
		Date from = request.getHoliday().getStartDate();
		Date to = request.getHoliday().getEndDate();
		// register holiday
		HolidayResponse response = new HolidayResponse();
		Set<Holiday> holidays = new HashSet<Holiday>();
		try {
			holidays = holidayManager.registerHoliday(username, from, to);
		} catch (IllegalStateException e) {
			// holiday not allowed
			logger.info(String.format(
					"holiday not granted for %s from %s to %s\n", username,
					from, to));
			// FIXME use XMLException
			response.setHolidayGranted(false);
			return response;
		}
		// build response
		HolidayList holidayList = new HolidayList();
		for(Holiday holiday : holidays) {
			HolidayType holidayType = new HolidayType();
			holidayType.setStartDate(holiday.getFrom());
			holidayType.setEndDate(holiday.getTo());
			holidayList.getHolidaies().add(holidayType);
		}
		logger.info(String.format(
				"accepted holiday for %s starting %s and ending %s\n", username,
				from, to));
		response.setHolidayGranted(true);
		response.setHolidayList(holidayList);
		return response;
	}

	@Resource
	public void setHolidayManager(HolidayManager holidayManager) {
		this.holidayManager = holidayManager;
	}
}