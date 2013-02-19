package org.randompage.samples.seam.wiki.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.randompage.samples.seam.wiki.client.EmptyResultException;
import org.randompage.samples.seam.wiki.client.UserManager;
import org.randompage.samples.seam.wiki.domain.LoginLog;
import org.randompage.samples.seam.wiki.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserManagerBean implements UserManager {
	private Logger logger = Logger.getLogger(UserManagerBean.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Query q = entityManager.createNamedQuery("User.findAll");
		return q.getResultList();
	}

	// TODO create Spring AOP finder ala WARP
	// TODO get rid of empty result exception, instead create boolean on finder
	// to switch between null return or NoResultException
	@Override
	public User getUser(String username) throws EmptyResultException {
		Query query = entityManager.createNamedQuery("User.findByUsername");
		query.setParameter("username", username);
		User user = null;
		try {
			user = (User) query.getSingleResult();
		} catch (NoResultException e) {
			throw new EmptyResultException("user: '" + username
					+ "' does not exists");
		}
		return user;
	}

	@Override
	public LoginLog saveLoginLog(LoginLog loginLog) {
		entityManager.persist(loginLog);
		return loginLog;
	}

	@Override
	public User saveUser(User user) {
		if (!entityManager.contains(user))
			user = entityManager.merge(user);
		entityManager.persist(user);
		logger.debug("saving user: " + user.toString());
		return user;
	}
}
