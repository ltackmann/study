package jpa.spring.impl;

import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import jpa.domain.Country;
import jpa.spring.api.CountryDao;

@Named
public class CountryDaoImpl extends AbstractDao<Country> implements CountryDao {
	public CountryDaoImpl() {
		setClazz(Country.class);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Country> findAll() {
		Query query = entityManager.createQuery("select c from Country c");

		return query.getResultList();
	}

	@Transactional(readOnly = true)
	public Country findByCountryCode(String code) {
		Query query = entityManager.createQuery("select c from Country c where c.code = :code");
		query.setParameter("code", code);

		return (Country) query.getSingleResult();
	}
}
