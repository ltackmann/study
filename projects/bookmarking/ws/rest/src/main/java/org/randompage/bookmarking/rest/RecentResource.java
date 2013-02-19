package org.randompage.bookmarking.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.randompage.bookmarking.api.domain.Bookmark;

@Path("/recent")
public class RecentResource {
	
	
	/**
	 * See recently posted bookmarks
	 * @return
	 */
	@GET
	public List<Bookmark> getBookmarks() {
		return null;
	}
	
	/**
	 * See recently posted bookmarks for a certain tag
	 */
	@GET
	@Path("tag")
	public List<Bookmark> getBookmarksByTag() {
		return null;
	}
}
