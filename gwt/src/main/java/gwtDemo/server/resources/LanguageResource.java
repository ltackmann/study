package gwtDemo.server.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import gwtDemo.server.dao.LanguageDao;
import gwtDemo.shared.LanguageMessage;

import javax.inject.Inject;

@Controller
@RequestMapping(value = "/messages")
public class LanguageResource {
    final Logger logger = LoggerFactory.getLogger(LanguageResource.class);

    private LanguageDao languageDao;

    /**
     * Get a single localized message using: GET /messages/{id}
     *
     * @param messageId
     * @param locale
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    LanguageMessage getMessage(@PathVariable("id") String messageId, @RequestHeader("Accept-Language") String locale) {
        logger.info("getting message with id: {} for locale: {}", messageId, locale);
        return languageDao.getMessage(locale, messageId);
    }


    @Inject
    public void setContentDao(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }
}
