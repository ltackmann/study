package org.randompage.bookmarking.rest;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.api.core.ResourceContext;
import org.randompage.bookmarking.api.UserManager;
import org.randompage.bookmarking.api.domain.User;

import org.randompage.bookmarking.rest.mappers.Mapper;
import org.randompage.bookmarking.ws.api.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Handles /users requests and dispatch requested from sub resources
 *
 * @author Lars Tackmann
 */
@Path("/users/")
public class UsersResource {
    final Logger logger = LoggerFactory.getLogger(UsersResource.class);
	private UserManager userManager;
	private UriInfo uriInfo;
	private ResourceContext resourceContext;
    private Mapper<User, UserType> userMapper;

    public UsersResource() {
        logger.debug("Constructing /users resource");
    }

    /**
     * Dispatch requests from /users/{email} sub resources
     *
     * @param email
     * @return
     */
	@Path("{email}")
	public UserResource getUser(@PathParam("email") String email) {
		User user = userManager.getUser(email);
		if (user == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
        }
        
		UserResource resource = resourceContext.getResource(UserResource.class);
		resource.setUser(user);
		return resource;
	}

	/**
	 * Create a user account: POST /users 
	 * 
	 * @return 
	 */
	@POST
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response createUser(UserType userType) {
		final String email = userType.getEmail();
		// create location URI that points from the resource
		UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
		URI uri = uriBuilder.path(email).build();

		if (userManager.getUser(email) != null) {
			return Response.status(Status.CONFLICT).location(uri).build();
        }
		userManager.createUser(userMapper.write(userType));

		return Response.created(uri).entity(userType).build();
	}

	@Context
	public void setResourceContext(ResourceContext resourceContext) {
		this.resourceContext = resourceContext;
	}

	@Context
	public void setUriInfo(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}

    @Inject
	public void setUserMapper(Mapper<User, UserType> userMapper) {
		this.userMapper = userMapper;
	}

	@Inject
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
}
