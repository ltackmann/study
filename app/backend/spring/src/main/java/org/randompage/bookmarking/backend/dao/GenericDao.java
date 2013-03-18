package org.randompage.bookmarking.backend.dao;

import org.randompage.bookmarking.api.domain.IEntity;

import java.io.Serializable;

/**
 * @author Lars Tackmann
 */
// Interface needed for correct CGLIB proxying pr http://forum.springsource.org/showthread.php?t=56205
public interface GenericDao<T extends IEntity<I>, I extends Serializable> {
    /**
     * Find entity by id
     *
     * @param id
     * @return
     */
    T find(I id);

    /**
     * Synchronize the persistence context to the underlying database. Then clear the context.
     */
    void flushAndClear();

    /**
     * Refresh entity with latest database entries
     *
     * @param entity
     */
    void refresh(T entity);

    /**
     * Remove entity by id
     *
     * @param id
     */
    void remove(I id);

    /**
     * Remove entities by their id's
     *
     * @param ids
     */
    void remove(I... ids);

    /**
     * Remove entity
     *
     * @param entity
     */
    void remove(T entity);

    /**
     * Remove entities
     *
     * @param entities
     */
    void remove(T... entities);

    /**
     * Save entity
     *
     * @param entity
     */
    void save(T entity);

    /**
     * Save entities
     *
     * @param entities
     */
	void save(T... entities);
}
