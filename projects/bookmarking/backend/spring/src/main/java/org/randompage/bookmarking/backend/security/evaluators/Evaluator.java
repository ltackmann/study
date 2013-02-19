package org.randompage.bookmarking.backend.security.evaluators;

import org.randompage.bookmarking.api.domain.User;

/**
 * Evaluate user permissions
 *
 * @author Lars Tackmann
 */
public interface Evaluator<T> {
    /**
     *
     * @param authenticatedUser Current authenticated user
     * @param targetObject Target that user must have access to
     * @return
     */
    boolean evaluate(User authenticatedUser, T targetObject);
}
