package jpa.api;

import jpa.domain.User;

public interface UserDao {
    User findUser(String username);
}
