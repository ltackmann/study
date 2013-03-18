package org.randompage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {
 public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPATestPU");
    EntityManager em = emf.createEntityManager();

    em.getTransaction().begin();

    MyEntity e = new MyEntity("some value");
    em.persist(e); /* (exception thrown here) */

    em.getTransaction().commit();

    em.close();
    emf.close();
}
}
