package org.randompage.bookmarking.backend.dao;

import org.randompage.bookmarking.api.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @author Lars Tackmann
 */
@Repository
public class UserDao extends GenericDaoImpl<User, Long> {

    public UserDao() {
        super(User.class);
    }

    public User findByEmail(String email) {
		return find("User.findByEmail", email);
	}
}
