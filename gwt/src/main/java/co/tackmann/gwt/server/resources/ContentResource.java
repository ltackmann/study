package co.tackmann.gwt.server.resources;

import org.randompage.bookmarking.api.ContentManager;
import org.randompage.bookmarking.api.domain.content.Message;
import org.randompage.bookmarking.api.domain.content.MessageGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/messages")
public class ContentResource {
    // TODO log via spring payload logger instead (to see HTTP headers)
    final Logger logger = LoggerFactory.getLogger(ContentResource.class);

    private ContentManager contentManager;

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
    Message getMessage(@PathVariable("id") String messageId, @RequestHeader("Accept-Language") String locale) {
        logger.info("getting message with id: {} for locale: {}", messageId, locale);
        return contentManager.getMessage(locale, messageId);
    }

    /**
     * Get group of localized messages using: GET /messages/groups/{id}
     *
     * @param groupId
     * @param locale
     * @return
     */
    @RequestMapping(value = "groups/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    MessageGroup getGroupMessages(@PathVariable("id") String groupId, @RequestHeader("Accept-Language") String locale) {
        logger.info("getting message group with id: {} for locale: {}", groupId, locale);
        return contentManager.getMessages(locale, groupId);
    }

    @Inject
    public void setContentManager(ContentManager contentManager) {
        this.contentManager = contentManager;
    }
}
