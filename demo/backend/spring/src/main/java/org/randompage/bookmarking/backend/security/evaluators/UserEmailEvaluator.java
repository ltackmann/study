package org.randompage.bookmarking.backend.security.evaluators;

import org.randompage.bookmarking.api.domain.Role;
import org.randompage.bookmarking.api.domain.User;

/**
 * Determine if authenticated user can manage user represented by email address
 *
 * @author Lars Tackmann
 */
public class UserEmailEvaluator implements Evaluator<String>{
    @Override
    public boolean evaluate(User authenticatedUser, String targetEmailAddress) {
        return authenticatedUser.getEmail().equals(targetEmailAddress);
    }
}