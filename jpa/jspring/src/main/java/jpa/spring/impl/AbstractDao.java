package jpa.spring.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao<T extends Serializable> {
	private Class<T> clazz;

	@PersistenceContext
	protected EntityManager entityManager;

	public void setClazz(Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T findOne(Long id) {
		return this.entityManager.find(this.clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return this.entityManager.createQuery("from " + this.clazz.getName()).getResultList();
	}

	public void save(T entity) {
		this.entityManager.persist(entity);
	}

	public void update(T entity) {
		this.entityManager.merge(entity);
	}

	public void delete(T entity) {
		this.entityManager.remove(entity);
	}

	public void deleteById(Long entityId) {
		T entity = this.findOne(entityId);
		this.delete(entity);
	}
}
