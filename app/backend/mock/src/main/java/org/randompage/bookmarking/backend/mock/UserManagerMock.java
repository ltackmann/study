package org.randompage.bookmarking.backend.mock;

import org.randompage.bookmarking.api.UserManager;
import org.randompage.bookmarking.api.domain.Role;
import org.randompage.bookmarking.api.domain.User;

import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named
class UserManagerMock implements UserManager {
    private static final Map<String, User> userDao = new HashMap<String, User>();

    static {
        User user = new User("John Doe", Role.USER, "user@company.com", "secret");
        // stuff dao
        userDao.put(user.getEmail(), user);
    }

    @Override
    public void deleteUser(User user) {
        throw new UnsupportedOperationException("Not mocked");
    }

    @Override
    public User getUser(String email) {
        return userDao.get(email);
    }

    @Override
    public User saveUser(User user) {
        final long min = 1;
        final long max = Character.MAX_VALUE;
        if(user.getId() == null || user.getId() < 1) {
            long id = min + (long)(Math.random() * ((max - min) + 1));
            user.setId(id);
        }
        
        userDao.put(user.getEmail(), user);
        return user;
    }
}
