package org.randompage.bookmarking.api;

import java.util.List;

import org.randompage.bookmarking.api.domain.Tag;
import org.randompage.bookmarking.api.domain.User;

/**
 * Manage tags
 *
 * @author Lars Tackmann
 */
public interface TagManager {
    /**
     * Get users tags
     * 
     * @param user
     * @return
     */
	List<Tag> getTags(User user);

    /**
     * Save tag. If tag is new then it is created otherwise it's updated
     * @param tag
     * @return
     */
	Tag saveTag(Tag tag);

    /**
     * Delete tag
     * 
     * @param tag
     */
	void deleteTag(Tag tag);
}
