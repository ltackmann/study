package org.randompage.bookmarking.frontend.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.randompage.bookmarking.frontend.shared.UserDTO;

/**
 * @author Lars Tackmann
 */
@RemoteServiceRelativePath("users.rpc")
public interface UserService extends RemoteService {
    /**
     * Authenticate user
     *
     * @param email
     * @param password
     * @return User or null if log in failed
     */
    UserDTO authenticate(String email, String password);

    /**
     * Create user
     *
     * @param user
     * @param password
     * @return True if user is created, false if user already exists
     */
    Boolean create(UserDTO user, String password);
}
