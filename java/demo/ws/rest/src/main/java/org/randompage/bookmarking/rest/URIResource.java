package org.randompage.bookmarking.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.randompage.bookmarking.api.UserManager;
import org.randompage.bookmarking.api.domain.User;

@Path("/uris/")
public class URIResource  {
	private UserManager userManager;
	
	/**
	 * See which users have bookmarked a certain URI
	 */
	@GET
	@Path("{URI-MD5}")
	public List<User> getUsers(@PathParam("URI-MD5") String uriMD5) {
		return null;
	}

	@Inject
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}
