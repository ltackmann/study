package org.randompage.bookmarking.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import org.randompage.bookmarking.api.domain.Bundle;

public class BundleResource {
	private final Bundle bundle;

	public BundleResource(Bundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * Group tags into a bundle
	 * 
	 * @return
	 */
	@POST
	public Bundle createBundle() {
		return bundle;
	}

	/**
	 * Fetch bundle
	 * 
	 * @return
	 */
	@GET
	public Bundle getBundle() {
		return bundle;
	}

	/**
	 * Modify bundle
	 */
	@PUT
	public void saveBundle(Bundle bundle) {
		throw new UnsupportedOperationException("TODO");
	}

	@DELETE
	public void deleteBundle(Bundle bundle) {
		throw new UnsupportedOperationException("TODO");
	}
}
