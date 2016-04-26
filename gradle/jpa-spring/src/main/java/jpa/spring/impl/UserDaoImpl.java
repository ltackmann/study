package jpa.spring.impl;

import javax.inject.Named;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import jpa.domain.User;
import jpa.spring.api.UserDao;

@Named
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
	public UserDaoImpl() {
		setClazz(User.class);
	}

	@Transactional
	public User createUser(User user) {
		if(user.getId() != null) {
			throw new IllegalArgumentException("user already created");
		}
		entityManager.persist(user);
		return user;
	}

	@Transactional(readOnly = true)
	public User findUser(String username) {
		Query query = entityManager.createNamedQuery("User.findByUsername");
		query.setParameter("username", username);

		return (User) query.getSingleResult();
	}
}
