package org.randompage.samples.jpa.api;

import org.randompage.samples.jpa.domain.User;

/**
 * Created by IntelliJ IDEA.
 * User: "Lars Tackmann"
 * Date: Dec 15, 2008
 * Time: 10:08:02 PM
 */
public interface UserDao {
    User findUser(String username);
}
