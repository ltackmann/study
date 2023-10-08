package org.randompage.bookmarking.backend.security.evaluators;

import org.randompage.bookmarking.api.domain.Role;
import org.randompage.bookmarking.api.domain.User;

/**
 * Determine if user can create other user 
 *
 * @author Lars Tackmann
 */
public class UserSaveEvaluator implements Evaluator<User> {
    @Override
    public boolean evaluate(User authenticatedUser, User targetUser) {
        if(authenticatedUser == null) {
            // anonymous can only create new users
            return (targetUser.getRole().equals(Role.USER) && targetUser.getId() == null);
        }
        return (authenticatedUser.equals(targetUser));
    }
}
