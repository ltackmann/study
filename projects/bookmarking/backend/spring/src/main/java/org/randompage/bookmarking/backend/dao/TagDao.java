package org.randompage.bookmarking.backend.dao;

import java.util.List;

import org.randompage.bookmarking.api.domain.Tag;
import org.randompage.bookmarking.api.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author Lars Tackmann
 */
@Repository
public class TagDao extends GenericDaoImpl<Tag, Long> {

    public TagDao() {
        super(Tag.class);
    }

    /**
     * Find all tags for user
     *
     * @param user
     * @return
     */
    public List<Tag> getTagsByUser(User user) {
        return findAll("Tag.findByUser", user);
    }

}
