package gwtDemo.server.gwtServices;

import gwtDemo.client.service.UserService;
import gwtDemo.shared.domain.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


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
    
	@Override
	public User authenticate(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}
