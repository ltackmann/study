package org.randompage.bookmarking.backend.dao;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.randompage.bookmarking.api.domain.User;
import org.randompage.bookmarking.backend.testUtils.IntegrationTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.randompage.bookmarking.backend.testUtils.UserData.*;

/**
 * Test DB logic in the UserDao
 *
 * @author Lars Tackmann
 * @see org.randompage.bookmarking.backend.dao.UserDao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class UserDaoIT extends IntegrationTester {
    @Autowired
    protected UserDao userDao;

    @Before
    public void prepareTestData() throws Exception {
       setUpDBUnit("UserTestData.xml");
    }

    /**
     * Check that we can successfully retrieve a user by email
     *
     * @see org.randompage.bookmarking.backend.dao.UserDao#findByEmail(String)
     */
    @Test
    public void userRetrievalTest() {
        User userByEmail = userDao.findByEmail(ADMIN_1.email);
        assertThat(userByEmail, notNullValue());
    }

    /**
     * Assert behaviour when trying to retrieve a non-existing user
     *
     * @see org.randompage.bookmarking.backend.dao.UserDao#findByEmail(String)
     */
    @Test
    public void noSuchUserTest() {
        User userByEmail = userDao.findByEmail("no-exixting@user.com");
        assertThat(userByEmail, nullValue());
    }
}
