package co.tackmann.gwt.client.service;

import co.tackmann.gwt.shared.UserDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

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
