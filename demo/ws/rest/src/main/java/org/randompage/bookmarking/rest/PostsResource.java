package org.randompage.bookmarking.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.randompage.bookmarking.api.UserManager;
import org.randompage.bookmarking.api.domain.Bookmark;
import org.randompage.bookmarking.api.domain.User;

@Path("/posts/{username}")
public class PostsResource {
	private UserManager userManager;
	private final User user;

	public PostsResource(@PathParam("username") String username) {
		user = userManager.getUser(username);
	}

	/**
	 * Fetch all of users bookmarks
	 * 
	 * @return
	 */
	@GET
	@Path("bookmarks")
	public List<Bookmark> getBookmarks() {
		return null;
	}

	/**
	 * Search users bookmarks by date
	 * 
	 * @param name
	 * @return
	 */
	@GET
	public List<Bookmark> getBookmarks(@QueryParam("query") String name) {
		return null;
	}

	@GET
	@Path("bookmarks/{tag}")
	public List<Bookmark> getBookmarksByTag(@PathParam("tag") String tag) {
		return null;
	}
}
