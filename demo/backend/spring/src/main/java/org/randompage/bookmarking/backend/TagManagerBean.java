package org.randompage.bookmarking.backend;

import java.util.List;

import org.randompage.bookmarking.api.TagManager;
import org.randompage.bookmarking.api.domain.Tag;
import org.randompage.bookmarking.api.domain.User;
import org.randompage.bookmarking.backend.dao.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lars Tackmann
 */
@Service(value = "tagManager")
public class TagManagerBean implements TagManager {
	private TagDao tagDao;

	// TODO security
	public void deleteTag(Tag tag) {
		tagDao.remove(tag);
	}

	public List<Tag> getTags(User user) {
		return tagDao.getTagsByUser(user);
	}

	// TODO security
	public Tag saveTag(Tag tag) {
		tagDao.save(tag);
		return tag;
	}

	@Autowired
	public void setTagDao(TagDao tagDao) {
		this.tagDao = tagDao;
	}

}
