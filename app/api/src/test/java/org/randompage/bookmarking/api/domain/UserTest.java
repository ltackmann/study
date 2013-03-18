package org.randompage.bookmarking.api.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


/**
 * Test user shared object
 */
public class UserTest {
    /**
     * Verify that correct password encoding algorithm is used
     */
    @Test
    public void testPasswordEncoding() {
        User user = new User();
        user.setPassword("johnd");
        
        assertThat(user.getPassword(), equalTo("0db52b4ea61ffc3f58b4d21c237151a1"));
    }

}
