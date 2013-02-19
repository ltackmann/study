package org.randompage.bookmarking.api.domain;

import java.io.Serializable;

public interface IEntity<I extends Serializable> extends Serializable {
    /**
     * Get primary key.
     *
     * @return primary key
     */
    I getId();

     /**
     * Set primary key.
     *
     * @param id
     */
    void setId(I id);
}
