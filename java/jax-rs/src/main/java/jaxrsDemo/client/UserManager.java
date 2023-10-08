package jaxrsDemo.client;

import java.util.List;

import jaxrsDemo.domain.User;

/**
 * Manage users
 * 
 * @author Lars Tackmann
 * 
 */
public interface UserManager extends Secured {
	void createUser(User user);
	
	void deleteUser(User user);

	User getUser(String username);

	List<User> getUsers();

	void updateUser(User user);
}
