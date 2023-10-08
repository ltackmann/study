package jaxrsDemo.resources;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.randompage.samples.jaxrs.spring.client.UserManager;
import org.randompage.samples.jaxrs.spring.domain.User;
import org.randompage.samples.jaxrs.spring.model.UserBean;
import org.randompage.samples.jaxrs.spring.resources.converters.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Manages individual users, requires the user to be logged in
 * 
 * @author Lars Tackmann
 */
public final class UserResource {
	private final User user;
	private final UriInfo uriInfo;
	@Autowired
	private UserManager userManager;

	public UserResource(User user, UriInfo uriInfo) {
		this.user = user;
		this.uriInfo = uriInfo;
	}

	@DELETE
	public void deleteUser() {
		userManager.deleteUser(user);
	}

	@GET
	@Produces(MediaType.APPLICATION_XML)
	public UserBean getUserBean() {
		return UserConverter.fromUser(user);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateUser(UserBean userBean) {
		if (userBean.getUsername() != user.getUsername())
			throw new WebApplicationException(Status.CONFLICT);
		// update domain object
		user.setName(userBean.getName());
		Response response;
		try {
			userManager.updateUser(user);
			response = Response.ok().build();
		} catch (Exception e) {
			URI uri = uriInfo.getAbsolutePath();
			response = Response.status(Status.CONFLICT).location(uri).build();
		}
		return response;
	}
}
