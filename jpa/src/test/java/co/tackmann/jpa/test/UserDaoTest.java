package co.tackmann.jpa.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.tackmann.jpa.api.UserDao;
import co.tackmann.jpa.domain.User;
import co.tackmann.jpa.domain.UserType;
import co.tackmann.jpa.test.utils.SpringTester;

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
