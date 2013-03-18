package org.randompage.samples.jpa.test;

import org.randompage.samples.jpa.domain.Product;
import org.randompage.samples.jpa.test.utils.SpringTester;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import org.testng.annotations.Test;

import javax.persistence.*;
import java.util.List;

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
