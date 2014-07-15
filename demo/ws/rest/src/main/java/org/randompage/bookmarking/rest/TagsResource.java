package org.randompage.bookmarking.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;

import org.randompage.bookmarking.api.TagManager;
import org.randompage.bookmarking.api.domain.Tag;
import org.randompage.bookmarking.api.domain.User;

/**
 * Handle user tags
 * 
 * @author Lars Tackmann
 */
public class TagsResource  {
	private User user;
	private TagManager tagManager;

	/**
	 * Delete tag
	 */
	@PUT
	public void deleteTag(Tag tag) {
		// TODO check that tags are removed from bookmarks (but that bookmarks still exists)
		// TODO security
		tagManager.deleteTag(tag);
	}

	/**
	 * Fetch user's tag vocabulary
	 * 
	 * @return
	 */
	@GET
	public List<Tag> getTags() {
		return tagManager.getTags(user);
	}

	/**
	 * Create or rename tag
	 */
	@PUT
	public void saveTag(Tag tag) {
		// TODO security
		tagManager.saveTag(tag);
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
