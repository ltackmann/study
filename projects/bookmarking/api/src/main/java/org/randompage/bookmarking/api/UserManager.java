package org.randompage.bookmarking.api;

import org.randompage.bookmarking.api.domain.User;

/**
 * Manage system users
 *
 * @author Lars Tackmann
 */
public interface UserManager {
    /**
     * Delete user
     * 
     * @param user
     */
    void deleteUser(User user);

    /**
     * Lookup user by email
     *
     * @param email
     * @return User if exists else null
     */
    User getUser(String email);

    /**
     * Save user. If user is new then he is created otherwise user is updated
     * 
     * @param user
     * @return user if saved else null;
     */
    User saveUser(User user);
}
