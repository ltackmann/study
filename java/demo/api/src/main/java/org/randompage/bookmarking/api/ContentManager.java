package org.randompage.bookmarking.api;

import org.randompage.bookmarking.api.domain.content.Message;
import org.randompage.bookmarking.api.domain.content.MessageGroup;

import java.util.List;

/**
 * Manage message content system  
 *
 * @author Lars Tackmann
 */
public interface ContentManager {
    Message getMessage(String locale, String messageId);

    MessageGroup getMessages(String locale, String groupId);
}
