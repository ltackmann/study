package org.randompage.bookmarking.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.randompage.bookmarking.api.UserManager;
import org.randompage.bookmarking.api.domain.Role;
import org.randompage.bookmarking.api.domain.User;
import org.randompage.bookmarking.backend.dao.UserDao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import static org.randompage.bookmarking.backend.testUtils.UserData.*;

import org.randompage.bookmarking.backend.testUtils.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;


/**
 * Test security requirements for the UserManager
 *
 * @author Lars Tackmann
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mockSpring.xml"})
public class UserManagerTest {
    final Logger logger = LoggerFactory.getLogger(UserManagerTest.class);

    @Autowired
    protected UserManager userManager;

    // DAOs are used to circumvent security tests (only possible inside this module)
    @Autowired
    protected UserDao userDao;

    /**
     * Test security constraints for
     *
     * @see org.randompage.bookmarking.api.UserManager#deleteUser(org.randompage.bookmarking.api.domain.User)
     */
    @Test
    public void deleteUserSecurityTest() {
        // Admin must be able to delete any user
        {
            login(ADMIN_1.email, ADMIN_1.password);
            User user = userDao.findByEmail(USER_1.email);
            assertThat(user, notNullValue());
            userManager.deleteUser(user);
            verify(userDao).remove(user);
        }
        // User must be allowed to delete himself
        {
            login(USER_1.email, USER_1.password);
            User user = userDao.findByEmail(USER_1.email);
            assertThat(user, notNullValue());
            userManager.deleteUser(user);
            verify(userDao).remove(user);
        }
        // Non-admin user cannot delete other users
        {
            login(USER_1.email, USER_1.password);
            User user = userDao.findByEmail(USER_2.email);
            assertThat(user, notNullValue());
            boolean thrown = false;
            try {
                userManager.deleteUser(user);
            } catch (AccessDeniedException e) {
                thrown = true;
            }
            assertThat(thrown, equalTo(true));
            verify(userDao, never()).remove(user);
        }
    }

    /**
     * Test security constraints for
     *
     * @see org.randompage.bookmarking.api.UserManager#getUser(String)
     */
    @Test
    public void getUserSecurityTest() {
        // Admin must be able to access any user
        {
            login(ADMIN_1.email, ADMIN_1.password);
            User user = userManager.getUser(USER_1.email);
            assertThat(user, notNullValue());
            verify(userDao).findByEmail(USER_1.email);
        }
        // Anonymous user can access anyone (used during login process)
        {
            anonymousLogin();
            User user = userManager.getUser(USER_1.email);
            assertThat(user, notNullValue());
            verify(userDao).findByEmail(USER_1.email);
        }
        // User must be allowed to access himself
        {
            login(USER_1.email, USER_1.password);
            User user = userManager.getUser(USER_1.email);
            assertThat(user, notNullValue());
        }
        // Non-admin user cannot access other users
        {
            login(USER_1.email, USER_1.password);
            boolean thrown = false;
            try {
                userManager.getUser(USER_2.email);
            } catch (AccessDeniedException e) {
                thrown = true;
            }
            assertThat(thrown, equalTo(true));
            verify(userDao, never()).findByEmail(USER_2.email);
        }
        // Non-authenticated users cannot access anyone
        {
            logout();
            boolean thrown = false;
            try {
                userManager.getUser(USER_2.email);
            } catch (AuthenticationCredentialsNotFoundException e) {
                thrown = true;
            }
            assertThat(thrown, equalTo(true));
            verify(userDao, never()).findByEmail(anyString());
        }
    }


    /**
     * Test security constraints for
     *
     * @see org.randompage.bookmarking.api.UserManager#saveUser(org.randompage.bookmarking.api.domain.User)
     */
    @Test
    public void saveUserSecurityTest() {
        class UserBuilder {
            User buildUser(Role role) {
                return new User("New User", role, "newuser@bookmarking.com", "secret");
            }
        }
        UserBuilder userBuilder = new UserBuilder();
        // Admin must be able to create new user
        {
            login(ADMIN_1.email, ADMIN_1.password);
            User admin = userBuilder.buildUser(Role.ADMIN);
            userManager.saveUser(admin);
            verify(userDao).save(admin);
        }
        // User must be allowed to update himself
        {
            final String newEmail = "new@email.com";
            login(USER_1.email, USER_1.password);
            User user = userDao.findByEmail(USER_1.email);
            assertThat(user.getEmail(), not(equalTo(newEmail)));
            user.setEmail(newEmail);
            userManager.saveUser(user);
            verify(userDao).save(user);
        }
        // Non-admin user cannot save other users
        {
            login(USER_1.email, USER_1.password);
            User admin = userBuilder.buildUser(Role.ADMIN);
            boolean thrown = false;
            try {
                userManager.saveUser(admin);
            } catch (AccessDeniedException e) {
                thrown = true;
            }
            assertThat(thrown, equalTo(true));
            verify(userDao, never()).save(admin);
        }
        // Anonymous user can create new user
        {
            anonymousLogin();
            User user = userBuilder.buildUser(Role.USER);
            userManager.saveUser(user);
            verify(userDao).save(user);
        }
        // Anonymous user cannot save existing users
        {
            anonymousLogin();
            User user = userBuilder.buildUser(Role.USER);
            user.setId(42L);
            boolean thrown = false;
            try {
                userManager.saveUser(user);
            } catch (AccessDeniedException e) {
                thrown = true;
            }
            assertThat(thrown, equalTo(true));
            verify(userDao, never()).save(user);
        }
        // Anonymous user cannot create admins
        {
            anonymousLogin();
            User admin = userBuilder.buildUser(Role.ADMIN);
            boolean thrown = false;
            try {
                userManager.saveUser(admin);
            } catch (AccessDeniedException e) {
                thrown = true;
            }
            assertThat(thrown, equalTo(true));
            verify(userDao, never()).save(admin);
        }
        // Non-authenticated users cannot create other users
        {
            logout();
            User user = userBuilder.buildUser(Role.USER);
            boolean thrown = false;
            try {
                userManager.saveUser(user);
            } catch (AuthenticationCredentialsNotFoundException e) {
                thrown = true;
            }
            assertThat(thrown, equalTo(true));
            verify(userDao, never()).save(user);
        }

    }

    /*
     * Test helper methods
     */
    private void anonymousLogin() {
        logout();
        GrantedAuthority[] authorities = new GrantedAuthority[]{new GrantedAuthorityImpl("ROLE_ANONYMOUS")};
        Authentication anonymous = new AnonymousAuthenticationToken("anonymous-key", "anonymous", Arrays.asList(authorities));
        SecurityContextHolder.getContext().setAuthentication(anonymous);
    }

    private void login(String email, String password) {
        logout();
        logger.info("trying to login in as user: {} with password: {}", email, password);
        Authentication auth = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private void logout() {
        prepareMocks();
        SecurityContextHolder.clearContext();
    }

    private void prepareMocks() {
        reset(userDao);
        // setup mock data
        for (UserData userData : UserData.values()) {
            final Role role = Role.valueOf(userData.role);
            User user = spy(new User(userData.name(), role, userData.email, userData.password));
            user.setId(userData.id);
            when(userDao.findByEmail(userData.email)).thenReturn(user);
        }
    }
}
