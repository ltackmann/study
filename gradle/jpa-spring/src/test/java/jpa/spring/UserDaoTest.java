package jpa.spring;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import javax.inject.Inject;

import org.testng.annotations.Test;

import jpa.domain.Address;
import jpa.domain.Country;
import jpa.domain.Customer;
import jpa.domain.User;
import jpa.domain.UserType;
import jpa.spring.api.UserDao;
import jpa.spring.utils.SpringTester;

public class UserDaoTest extends SpringTester {
    @Inject
    private UserDao userDao;

    @Test
    public void createUserTest() {
    	 Country country = new Country("USA", "US");
         Address address = new Address("Quack Street", "1112", "Duckburg", country);
         Customer customer = new Customer("donald@duck.com", "donald", "secret", "Donald Duck", address);
         
         userDao.createUser(customer);
         assertThat(customer.getId(), notNullValue());
    }
    
    @Test(dependsOnMethods = "createUserTest")
    public void findUserTest() {
		User user = userDao.findUser("donald");
        
        assertThat(user.getName(), is("Donald Duck"));
        assertThat(user.getUserType(), equalTo(UserType.CUSTOMER));
	}
}
