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

import co.tackmann.jpa.domain.User;
import co.tackmann.jpa.domain.UserType;

/**
 * Show how a entitymanager can be created by hand
 */
public class EntityManagerFactoryTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeClass
    public void setUp() {
        // misc JPA
        entityManagerFactory = Persistence.createEntityManagerFactory("example");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void userTest() {
        Query query = entityManager.createQuery("select u from User u where u.username = 'jd'");
        User user = (User) query.getSingleResult();

        assertThat(user.getUsername(), is("jd"));
        assertThat(user.getUserType(), is(UserType.CUSTOMER));

    }

    @Test
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
    public void tearDown() {
        // Stop JPA
        if (entityManager != null)
            entityManager.close();
        if (entityManagerFactory != null)
            entityManagerFactory.close();
    }
}