package org.randompage.bookmarking.rest.mappers;

import org.randompage.bookmarking.api.domain.Role;
import org.randompage.bookmarking.api.domain.User;
import org.randompage.bookmarking.ws.api.UserType;

import java.util.Date;

class UserMapper implements Mapper<User, UserType> {
    @Override
    public UserType read(User user) {
        UserType userType = new UserType();
        userType.setEmail(user.getEmail());
        userType.setName(user.getName());
        userType.setTimestamp(new Date());
        return null;
    }

    @Override
    public User write(UserType userType) {
        return new User(userType.getName(), Role.USER, userType.getEmail(), userType.getPassword());
    }
}
