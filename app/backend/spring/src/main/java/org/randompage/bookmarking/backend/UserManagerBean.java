package org.randompage.bookmarking.backend;

import org.randompage.bookmarking.api.UserManager;
import org.randompage.bookmarking.api.domain.User;
import org.randompage.bookmarking.backend.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @author Lars Tackmann
 */
@Service(value = "userManager")
public class UserManagerBean implements UserManager {
    private UserDao userDao;


    @PreAuthorize("hasRole('ROLE_ADMIN') or hasPermission(#user,'delete_user')")
    public void deleteUser(User user) {
        userDao.remove(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or isAnonymous() or hasPermission(#email,'manage_email')")
    public User getUser(String email) {
        return userDao.findByEmail(email);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN') or hasPermission(#user,'save_user')")
    public User saveUser(User user) {
        userDao.save(user);
        return user;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
