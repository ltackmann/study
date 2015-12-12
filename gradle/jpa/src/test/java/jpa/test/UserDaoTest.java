package jpa.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.inject.Inject;

import org.testng.annotations.Test;

import jpa.api.UserDao;
import jpa.domain.User;
import jpa.domain.UserType;
import jpa.test.utils.SpringTester;

public class UserDaoTest extends SpringTester {
    @Inject
    private UserDao userDao;

    @Test
    public void userTest() {
		User user = userDao.findUser("donald");
        
        assertThat(user.getName(), equals("Donald Duck"));
        assertThat(user.getUserType(), equalTo(UserType.CUSTOMER));
	}
}
