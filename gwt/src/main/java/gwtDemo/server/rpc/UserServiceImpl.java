package gwtDemo.server.rpc;

import gwtDemo.client.service.UserService;
import gwtDemo.server.dao.LanguageDao;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	private LanguageDao languageDao;

    @Override
    public Set<String> getLanguages() {
    	return languageDao.getLanguages();
    }
    
    @Inject
    public void setContentDao(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

	@Override
	public String getCurrentLanguage() {
		return "English";
	}
}
