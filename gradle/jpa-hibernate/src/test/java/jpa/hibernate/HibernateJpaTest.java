package jpa.hibernate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import org.dbunit.DatabaseUnitException;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import jpa.domain.Address;
import jpa.domain.Country;
import jpa.domain.Customer;
import jpa.domain.User;
import jpa.hibernate.HibernateManager;
import jpa.hibernate.utils.DatabaseProduct;
import jpa.hibernate.utils.ExportDatabase;

/**
 * Test creating an entity manager using {@link Persistence} and persistence.xml
 */
public class HibernateJpaTest {
    private static HibernateManager hibernateManager;
    private static EntityManager entityManager;

    @BeforeClass
    public static void setUp() {    	
    	// "jdbc:hsqldb:file:~/Projects/java/gradle/hsql.db"
    	hibernateManager = HibernateManagerFactory.getTestHibernateManager("jpa-test", DatabaseProduct.HSQLDB, "jdbc:hsqldb:mem:mymemdb", "jpa.domain");
    	//hibernateManager = HibernateManagerFactory.getTestHibernateManager("jpa-test", DatabaseProduct.HSQLDB, "jdbc:hsqldb:mem:mymemdb");
    	entityManager = hibernateManager.getEntityManagerFactory().createEntityManager();
    }
    
    @Test
    public void exportDatabaseSchemaTest() {   	
    	hibernateManager.createSchema("schema-test.sql");
    }
    
    @Test
    public void exportDatabaseDataTest() {
    	Session session = entityManager.unwrap(Session.class);
    	session.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				assertThat(connection, notNullValue());
				try {
					ExportDatabase data = new ExportDatabase(connection);
					data.dumpData("database-data.xml");
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
    	});
    }
    
    @Test
    public void entityCreationTest() throws Exception {
    	UserTransaction tx = hibernateManager.getTransaction();
        
        Country country = new Country("USA", "US");
        Address address = new Address("Quack Street", "1112", "Duckburg", country);
        Customer customer = new Customer("donald@duck.com", "donald", "secret", "Donald Duck", address);
        assertThat(customer.getId(), nullValue());
        
        tx.begin();  
        entityManager.persist(customer); 
        entityManager.flush();
        tx.commit();       
        assertThat(customer.getId(), notNullValue());
    }
    
    @Test(dependsOnMethods = "entityCreationTest")
    public void entityQueryTest() throws Exception {    
        UserTransaction tx = hibernateManager.getTransaction();
        tx.begin();
        Query query = entityManager.createNamedQuery("User.findByUsername");
        query.setParameter("username", "donald");

		User user = (User) query.getSingleResult();
        assertThat(user, notNullValue());
        assertThat(user.getName(), is("Donald Duck"));
        tx.rollback();
    }

    @AfterClass
    public static void tearDown() {
        if (hibernateManager != null) {
        	hibernateManager.close();
        }
    }
}