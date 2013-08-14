package co.tackmann.jpa.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.tackmann.jpa.api.UserDao;
import co.tackmann.jpa.domain.User;

@Repository
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
