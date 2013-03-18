package org.randompage.samples.jpa.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.randompage.samples.jpa.api.UserDao;
import org.randompage.samples.jpa.domain.User;
import org.randompage.samples.jpa.domain.UserType;
import org.randompage.samples.jpa.test.utils.SpringTester;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * User: "Lars Tackmann"
 * Date: Dec 14, 2008
 */
public class UserDaoTest extends SpringTester{
    @Autowired
    private UserDao userDao;

    @Test
    public void userTest() {
		User user = userDao.findUser("jd");
        
        assertThat(user.getUsername(), is("jd"));
        assertThat(user.getUserType(), is(UserType.CUSTOMER));
	}
}
