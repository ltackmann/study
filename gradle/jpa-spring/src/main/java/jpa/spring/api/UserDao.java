package jpa.spring.api;

import jpa.domain.User;

public interface UserDao {
    User createUser(User user);
	
    User findUser(String username);
}
