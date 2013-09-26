package gwtDemo.server.rpc;

import gwtDemo.client.service.LanguageService;
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
public class LanguageServiceImpl extends BaseServlet implements LanguageService {
    final Logger logger = LoggerFactory.getLogger(LanguageServiceImpl.class);
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
