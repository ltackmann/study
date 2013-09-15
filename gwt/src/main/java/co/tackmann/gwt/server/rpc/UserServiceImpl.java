package org.randompage.bookmarking.frontend.server.rpc;

import org.randompage.bookmarking.api.UserManager;
import org.randompage.bookmarking.api.domain.Role;
import org.randompage.bookmarking.api.domain.User;
import org.randompage.bookmarking.frontend.server.rpc.BaseServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import co.tackmann.gwt.client.service.UserService;
import co.tackmann.gwt.shared.UserDTO;

import javax.inject.Inject;


/**
 * The server side implementation of the RPC service.
 *
 * @author Lars Tackmann
 */
@Controller
@RequestMapping("/bookmarking/users.rpc")
public class UserServiceImpl extends BaseServlet implements UserService {
    final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final long serialVersionUID = 1040457405556464059L;
	private UserManager userManager;

	public UserDTO authenticate(String email, String password) {
        User user = userManager.getUser(email);
        // TODO replace with real Spring security auth check
        if(user == null) {
            logger.info("user: {0} not found", email);
            return null;
        }
		return userMapper(user);
	}

    @Override
    public Boolean create(UserDTO userDTO, String password) {
        User user = userMapper(userDTO, password);
        user = userManager.saveUser(user);
        if(user == null) {
            logger.warn("failed to create user account for {0}", user.getEmail());
            return false;
        } else {
            logger.debug("created user account for {0}", user.getEmail());
            return true;
        }

    }

    @Inject
	public void setUserManger(UserManager userManager) {
		this.userManager = userManager;
	}

    private UserDTO userMapper(User user) {
        return new UserDTO(user.getName(), user.getRole(), user.getEmail());
    }

    private User userMapper(UserDTO user, String password) {
        return new User(user.getName(), Role.USER, user.getEmail(), password); 
    }
}
