package org.randompage.bookmarking.backend.security.evaluators;

import org.randompage.bookmarking.api.domain.User;

/**
 * Determine if authenticated user can manage other user
 *
 * @author Lars Tackmann
 */
public class UserDeleteEvaluator implements Evaluator<User> {
    @Override
    public boolean evaluate(User authenticatedUser, User targetUser) {
        return (authenticatedUser.equals(targetUser));
    }
}
