package jpa.spring;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.testng.annotations.Test;

import jpa.domain.Address;
import jpa.domain.Country;
import jpa.domain.Customer;
import jpa.domain.User;
import jpa.spring.utils.SpringTester;

public class SpringJpaTest extends SpringTester {
    @PersistenceContext
    private EntityManager entityManager;
    @Resource
    PlatformTransactionManager transactionManager;
    
    @Test
    public void entityCreationTest() {
    	 Country country = new Country("USA", "US");
         Address address = new Address("Quack Street", "1112", "Duckburg", country);
         Customer customer = new Customer("donald@duck.com", "donald", "secret", "Donald Duck", address);
         
         execute(new TransactionCallback<Object>() {
			@Override
			public Object doInTransaction(TransactionStatus status) {
				 entityManager.persist(customer);
				 return customer;
			}
		});  
    }

    @Test(dependsOnMethods = "entityCreationTest")
    public void namedQueryTest() {
        Query query = entityManager.createNamedQuery("User.findByUsername");
        query.setParameter("username", "donald");

        User user = (User) query.getSingleResult();
        assertThat(user, notNullValue());
        assertThat(user.getName(), is("Donald Duck"));
    }
    
    private void execute(TransactionCallback<Object> transactionCallback) {
    	TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
    	transactionTemplate.execute(transactionCallback);
    }
}
