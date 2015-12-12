package jpa.impl;

import javax.inject.Named;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import jpa.api.UserDao;
import jpa.domain.User;

@Named
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
	public UserDaoImpl() {
		setClazz(User.class);
	}
	
    @Transactional(readOnly = true)
    public User findUser(String username) {
        Query query = entityManager.createNamedQuery("User.findByUsername");
        query.setParameter("username",username);

        return (User) query.getSingleResult();
    }
}
