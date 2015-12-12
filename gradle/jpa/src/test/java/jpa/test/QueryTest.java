package jpa.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.testng.annotations.Test;

import jpa.domain.Product;
import jpa.test.utils.SpringTester;

@SuppressWarnings("unchecked")
public class QueryTest extends SpringTester {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void productListTest() {
        Query query = entityManager.createNamedQuery("Product.findInList");
        query.setParameter(1, "Pro Spring 2.5");

		List<Product> products = query.getResultList();
        assertThat(products, notNullValue());
        assertThat(products.size(), greaterThan(0));
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
}
