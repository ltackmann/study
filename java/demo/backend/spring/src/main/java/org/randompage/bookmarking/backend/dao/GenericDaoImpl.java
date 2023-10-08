package org.randompage.bookmarking.backend.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.randompage.bookmarking.api.domain.IEntity;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lars Tackmann
 */
@Transactional
abstract class GenericDaoImpl<T extends IEntity<I>, I extends Serializable> implements GenericDao<T,I> {
	protected Class<T> clazz;
	protected EntityManager entityManager;

	@SuppressWarnings(value = "unchecked")
	protected GenericDaoImpl(Class<T> returnedClass) {
        // TODO remove hardcoded return class
        // Get actual type class (works since type parameters are being erased only from the
        // dynamic types). More info here http://www.artima.com/weblogs/viewpost.jsp?thread=208860
		//clazz = (Class<T>) ((ParameterizedType) getClass()
		//		.getGenericSuperclass()).getActualTypeArguments()[0];
        clazz = returnedClass;
	}

    @Transactional(readOnly=true)
    @SuppressWarnings("unchecked")
	public T find(I id) {
		return entityManager.find(clazz, id);
	}

	/**
	 * Find single entity by named query
	 * 
	 * @param queryName
	 * @param args
	 * @return
	 */
    @Transactional(readOnly=true)
	protected T find(String queryName, Object... args) {
		List<T> listResult = findAll(queryName, args);
		if (listResult.size() == 0) {
			return null;
        } else if (listResult.size() > 1) {
			throw new NonUniqueResultException();
        }
		return listResult.get(0);
	}

	/**
	 * Find entities by named query
	 * 
	 * @param queryName
	 * @param args
	 * @return
	 */
    @Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	protected List<T> findAll(String queryName, Object... args) {
		Query query = entityManager.createNamedQuery(queryName);
		int pos = 1;
		for (Object arg : args) {
			query.setParameter(pos, arg);
        }
		return query.getResultList();
	}

	public void flushAndClear() {
		entityManager.flush();
		entityManager.clear();
	}

	public void refresh(T entity) {
		entityManager.refresh(entity);
	}

	public void remove(I id) {
		remove(find(id));
	}

	public void remove(I... ids) {
		for(I id : ids) {
			remove(find(id));
		}
	}

	public void remove(T entity) {
		if (!entityManager.contains(entity)) {
			entity = entityManager.merge(entity);
        }
		entityManager.remove(entity);
	}

	public void remove(T... entities) {
		for(T entity : entities)
			remove(entity);
	}

	public void save(T entity) {
		if (entity.getId() == null) {
			entityManager.persist(entity); // SQL insert
        } else {
			entityManager.merge(entity); // SQL update
        }
	}

	public void save(T... entities) {
		for (T entity : entities) {
			save(entity);
		}
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
