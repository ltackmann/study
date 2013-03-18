package org.randompage.samples.jaxrs.spring.impl;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.randompage.samples.jaxrs.spring.client.UserManager;
import org.randompage.samples.jaxrs.spring.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserManagerBean implements UserManager {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void createUser(User user) {
		entityManager.persist(user);
	}

	@Override
	@Transactional
	@RolesAllowed( { ROLE_ADMIN, ROLE_USER })
	public void deleteUser(User user) {
		user = entityManager.merge(user);
		entityManager.remove(user);
	}
	
	@Override
	@Transactional(readOnly = true)
	public User getUser(String username) {
		Query query = entityManager
				.createQuery("from User u where u.username = :username");
		query.setParameter("username", username);
		User user = null;
		try {
			user = (User) query.getSingleResult();
		} catch (NoResultException e) {
			// fall through when user does not exists (i.e. return null)
		}
		return user;
	}


	@Override
	@RolesAllowed(ROLE_ADMIN)
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<User> getUsers() {
		Query query = entityManager.createQuery("select u from User u");
		return query.getResultList();
	}

	@Override
	@Transactional
	@RolesAllowed( { ROLE_ADMIN, ROLE_USER })
	public void updateUser(User user) {
		entityManager.merge(user);
	}
}
