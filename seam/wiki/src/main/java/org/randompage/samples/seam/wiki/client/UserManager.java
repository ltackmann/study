package org.randompage.samples.seam.wiki.client;

import java.util.List;

import org.randompage.samples.seam.wiki.domain.LoginLog;
import org.randompage.samples.seam.wiki.domain.User;

/**
 * Manager users
 * 
 * @author Lars Tackmann
 */
public interface UserManager {
	List<User> getAllUsers();

	User saveUser(User user);

	User getUser(String username) throws EmptyResultException;

	LoginLog saveLoginLog(LoginLog loginLog);
}
