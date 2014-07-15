package org.randompage.bookmarking.backend.security;

import org.randompage.bookmarking.api.domain.User;
import org.randompage.bookmarking.backend.dao.UserDao;
import org.randompage.bookmarking.backend.security.evaluators.Evaluator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.Map;

/**
 * Custom permission evaluator that delegates security checks to our own Evaluators
 *
 * @see org.randompage.bookmarking.backend.security.evaluators.Evaluator
 */
public class PermissionEvaluatorImpl implements PermissionEvaluator {
    final Logger logger = LoggerFactory.getLogger(PermissionEvaluatorImpl.class);

    private Map<String, Evaluator> permissionEvaluators;
    private UserDao userDao;

    // evaluate a permission for a given domain object

    public boolean hasPermission(Authentication authentication, Object domainObject, Object permission) {
        // ensure we have enough data to safely handle permission check 
        if (domainObject == null) {
            logger.error("null conditions must be handled directly in the security expression");
            return false;
        } else if (authentication.getName() == null) {
            logger.error("anonymous conditions must be handled directly in the security expression");
            return false;
        } else if (!(permission instanceof String)) {
            logger.error("unknown permission type: {}", permission);
            return false;
        }

        // lookup and use custom security evaluator
        String privilege = (String) permission;
        Evaluator evaluator = permissionEvaluators.get(privilege);
        if (evaluator == null) {
            logger.error("unknown permission check {}", privilege);
            return false;
        }
        // authentication name property holds the users email address
        User authenticatedUser = userDao.findByEmail(authentication.getName());
        boolean access = evaluator.evaluate(authenticatedUser, domainObject);

        final String principal = (authentication.getPrincipal().equals("anonymous")) ? "anonymous" : authenticatedUser.getEmail();
        logger.debug("user: {} access privilege {}", principal + (access == true ? " have" : " does not have"), privilege);

        return access;
    }

    // evaluate a permission where only the identifier of the target object is available

    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        logger.error("no permission granted for target: {} with id: {}", targetType, targetId);
        return false;
    }

    @Autowired
    public void setPermissionEvaluators(Map<String, Evaluator> permissionEvaluators) {
        this.permissionEvaluators = permissionEvaluators;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
