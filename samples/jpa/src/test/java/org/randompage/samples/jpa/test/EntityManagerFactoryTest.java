package org.randompage.samples.jpa.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.randompage.samples.jpa.domain.User;
import org.randompage.samples.jpa.domain.UserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.eclipse.persistence.sessions.server.Server;
import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.internal.sessions.UnitOfWorkImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * User: "Lars Tackmann"
 * Date: Dec 14, 2008
 * <p/>
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

    @Test(description = "should execute a stored procedure")
    public void storedProcedureTest() throws SQLException {
        entityManager.getTransaction().begin();
        Query query = entityManager.createNativeQuery("select get_user('jd')");
        ResultSet resultSet = (ResultSet) query.getSingleResult();
        resultSet.next();
        String username = resultSet.getString("username");
        assertThat(username, is("jd"));
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