package org.randompage.bookmarking.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.UriInfo;

import org.randompage.bookmarking.api.domain.User;

public class CalendarResource  {

	public CalendarResource(UriInfo uriInfo, User user) {

	}

	/**
	 * Fetch users posting history
	 * 
	 * @return
	 */
	@GET
	public List<Date> getHistory() {
		return null;
	}
	
	@GET
	@Path("tag")
	public List<Date> getHistory(@PathParam("tag") String tag) {
		return null;
	}
}
