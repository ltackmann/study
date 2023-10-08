package jaxrsDemo.impl;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

import jaxrsDemo.client.UserManager;
import jaxrsDemo.domain.User;

import org.springframework.stereotype.Repository;

@Repository
public class UserManagerBean implements UserManager {
	@SuppressWarnings("serial")
	private static Map<String, User> users = new LinkedHashMap<String, User>() {{ 
		this.put("ltackmann", new User("Lars Tackmann", "ltackmann", "secret"));
	}};

	@Override
	public void createUser(User user) {
		if(users.containsKey(user.getUsername())) {
			throw new IllegalStateException("user " + user.getUsername() + " already exists");
		}
		users.put(user.getUsername(), user);
	}

	@Override
	@RolesAllowed( { ROLE_ADMIN, ROLE_USER })
	public void deleteUser(User user) {
		users.remove(user.getUsername());
	}
	
	@Override
	public User getUser(String username) {
		return users.get(username);
	}

	@Override
	@RolesAllowed(ROLE_ADMIN)
	public List<User> getUsers() {
		return new LinkedList<User>(users.values());
	}

	@Override
	@RolesAllowed( { ROLE_ADMIN, ROLE_USER })
	public void updateUser(User user) {
		if(!users.containsKey(user.getUsername())) {
			throw new IllegalStateException("user " + user.getUsername() + " does not exists");
		}
		users.put(user.getUsername(), user);
	}
}
