package org.randompage.bookmarking.rest;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.core.ResourceContext;
import org.randompage.bookmarking.api.domain.Bookmark;
import org.randompage.bookmarking.api.domain.User;

/**
 * sub resource from handle /users/{email}/bookmarks requests
 *
 * @author Lars Tackmann
 */
public class BookmarksResource {
    private User user;
    private UriInfo uriInfo;
    private ResourceContext resourceContext;

    @GET
    public List<Bookmark> getBookmarks(@QueryParam("query") String name) {
        return null;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createBookmark() {
        throw new UnsupportedOperationException();
    }

	@GET
	@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public String getBookmark() {
		throw new UnsupportedOperationException();
	}

	@PUT
	@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void modifyBookmark() {
		throw new UnsupportedOperationException();
	}

	@DELETE
	public void deleteBookmark() {
		throw new UnsupportedOperationException();
	}

    @Context
	public void setResourceContext(ResourceContext resourceContext) {
		this.resourceContext = resourceContext;
	}

    @Context
    public void setUriInfo(UriInfo uriInfo) {
        this.uriInfo = uriInfo;
    }

    public void setUser(User user) {
		this.user = user;
	}
}
