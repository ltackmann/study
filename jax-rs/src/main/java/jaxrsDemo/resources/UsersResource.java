package jaxrsDemo.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.randompage.samples.jaxrs.spring.client.UserManager;
import org.randompage.samples.jaxrs.spring.domain.User;
import org.randompage.samples.jaxrs.spring.model.UserBean;
import org.randompage.samples.jaxrs.spring.model.UserBeanList;
import org.randompage.samples.jaxrs.spring.resources.converters.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.sun.jersey.spi.resource.Singleton;

/**
 * Handles and dispatches user requests
 * 
 * @author Lars Tackmann
 * 
 */
@Singleton
@Component
@Path("users")
public final class UsersResource {
	@Autowired
	private UserManager userManager;
	@Autowired
	private ApplicationContext context;
	@Context
	private UriInfo uriInfo;

	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response createUser(UserBean userBean) {
		String username = userBean.getUsername();
		User user = new User(userBean.getName(), userBean.getUsername(),
				userBean.getPassword());
		// create location URI that points to the new resource
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
		URI uri = uriBuilder.path(username).build();
		// persist user
		try {
			userManager.createUser(user);
		} catch (Exception e) {
			return Response.status(Status.CONFLICT).location(uri).build();
		}
		return Response.created(uri).entity(user).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public UserBeanList getUsers() {
		UserBeanList userList = new UserBeanList();
		List<User> users = userManager.getUsers();
		for(User user : users) {
			UserBean userBean = UserConverter.fromUser(user);
			userList.getUsers().add(userBean);
		}
		return userList;
	}

	@Path("{username}")
	public UserResource getUserResource(@PathParam("username") String username) {
		User user = userManager.getUser(username);
		if (user == null)
			throw new WebApplicationException(Status.NOT_FOUND);
		UserResource resource = new UserResource(user, uriInfo);
		context.getAutowireCapableBeanFactory().autowireBean(resource);
		return resource;
	}
}
