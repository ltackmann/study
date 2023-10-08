package org.randompage.bookmarking.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.randompage.bookmarking.api.UserManager;
import org.randompage.bookmarking.api.domain.User;

import com.sun.jersey.api.core.ResourceContext;

/**
 * sub resource from handle /users/{email} requests and dispatch requested from sub resources
 * 
 * @author Lars Tackmann
 */
public class UserResource {
	private User user;
	private UserManager userManager;
	private ResourceContext resourceContext;

	/**
	 * Delete a user account: DELETE /users/{email} 
	 */
	@DELETE
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void deleteUser() {
		userManager.deleteUser(user);
	}

	@Path("bookmarks")
	public BookmarksResource getBookmarksResource() {
        BookmarksResource resource = resourceContext.getResource(BookmarksResource.class);
        resource.setUser(user);
		return resource;
	}

	@Path("bundles")
	public BundlesResource getBundlesResource() {
		return new BundlesResource(null, null);
	}

	@Path("calendar")
	public CalendarResource getCalendarResource() {
		return new CalendarResource(null, null);
	}

	@Path("tags")
	public TagsResource getTagsResource() {
		TagsResource resource = resourceContext.getResource(TagsResource.class);
		resource.setUser(user);
		return resource;
	}

	/**
	 * View a user account: GET /users/{email}
	 * 
	 * @return
	 */
	@GET
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public User getUser() {
		return user;
	}

	/**
	 * Modify a user account: PUT /users/{email}
	 */
	@PUT
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void modifyUser() {
		userManager.modifyUser(user);
	}

	@Context
	public void setResourceContext(ResourceContext resourceContext) {
		this.resourceContext = resourceContext;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Inject
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}
