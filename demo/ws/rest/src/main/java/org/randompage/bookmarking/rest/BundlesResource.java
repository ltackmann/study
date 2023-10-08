package org.randompage.bookmarking.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.UriInfo;

import org.randompage.bookmarking.api.domain.Bundle;
import org.randompage.bookmarking.api.domain.User;

/**
 * Handle user bundles
 * 
 * @author Lars Tackmann
 */
public class BundlesResource  {

	public BundlesResource(UriInfo uriInfo, User user) {
		
	}

	/**
	 * Fetch user's tag bundles
	 * @return
	 */
	@GET
	public List<Bundle> getBundles() {
		return null;
	}
	
	@Path("{bundle}")
	public BundleResource getBundleResource(String boundleName) {
		return new BundleResource(null);
	}
}
