package org.randompage.samples.jpa.impl;

import org.randompage.samples.jpa.api.UserDao;
import org.randompage.samples.jpa.domain.User;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * User: "Lars Tackmann"
 * Date: Dec 15, 2008
 */
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public User findUser(String username) {
        Query query = entityManager.createNamedQuery("User.findByUsername");
        query.setParameter("username",username);

        return (User) query.getSingleResult();
    }
}
