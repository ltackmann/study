package co.tackmann.jpa.api;

import co.tackmann.jpa.domain.User;

public interface UserDao {
    User findUser(String username);
}
