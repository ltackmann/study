package co.tackmann.jpa.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import co.tackmann.jpa.domain.Address;
import co.tackmann.jpa.domain.Country;
import co.tackmann.jpa.domain.Customer;
import co.tackmann.jpa.domain.User;
import co.tackmann.jpa.domain.UserType;

/**
 * Test creating an entitymanager by hand 
 */
public class EntityManagerFactoryTest {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    @BeforeClass
    public static void setUp() {
        // TODO can this be loaded from java instead of persistence.xml ?
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        entityManager = entityManagerFactory.createEntityManager();
        // create test data
        Query query = entityManager.createQuery("select c from Country c where c.code = 'US'");
        if(query.getResultList().isEmpty()) {
	        entityManager.getTransaction().begin();
	        Country country = new Country("USA", "US");
	        Address address = new Address("Quack Street", "1112", "Duckburg", country);
	        Customer customer = new Customer("donald@duck.com", "donald", "secret", "Donald Duck", address);
	        entityManager.persist(customer);
	        entityManager.getTransaction().commit();
        }
    }
    
    @Test
    public void userTest() {
        Query query = entityManager.createQuery("select u from User u where u.username = 'donald'");
        User user = (User) query.getSingleResult();

        assertThat(user.getName(), is("Donald Duck"));
        assertThat(user.getUserType(), is(UserType.CUSTOMER));
    }
    
	@Test
	public void unwrapAndUseConnection() {
		// TODO unwrap and use connection (see HsqlDbTest)
	}

    //@Test
    public void storedProcedureTest() throws SQLException {
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("select get_user('jd')");
        ResultSet resultSet = (ResultSet) query.getSingleResult();
        resultSet.next();
        String username = resultSet.getString("username");
        assertThat("should execute a stored procedure", username, is("jd"));
        entityManager.getTransaction().rollback();
    }

    @AfterClass
    public static void tearDown() {
        // Stop JPA
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}