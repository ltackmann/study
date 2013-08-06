package co.tackmann.jpa.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Test;

import co.tackmann.jpa.domain.Product;
import co.tackmann.jpa.test.utils.SpringTester;

/**
 * User: "Lars Tackmann"
 * Date: Dec 16, 2008
 */
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
}
