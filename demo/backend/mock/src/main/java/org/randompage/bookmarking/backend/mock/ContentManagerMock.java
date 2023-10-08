package org.randompage.bookmarking.backend.mock;

import org.randompage.bookmarking.api.ContentManager;
import org.randompage.bookmarking.api.domain.content.Message;
import org.randompage.bookmarking.api.domain.content.MessageGroup;

import javax.inject.Named;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Named
public class ContentManagerMock implements ContentManager {
    private static Map<String, List<MessageGroup>> contentDao = new HashMap<String, List<MessageGroup>>();

    static {
        List<MessageGroup> usMessages = new LinkedList<MessageGroup>();

        // group messages
        MessageGroup bannerMessages = new MessageGroup("banner");
        bannerMessages.getMessages().add(new Message("msg-1", "Discover, Bookmark, Share..."));
        bannerMessages.getMessages().add(new Message("msg-2", "Ready...Set...Bookmark!"));
        usMessages.add(bannerMessages);

        MessageGroup frontPage = new MessageGroup("frontpage");
        frontPage.getMessages().add(new Message("welcome-msg", "Welcome to bookmarking"));
        usMessages.add(frontPage);

        contentDao.put("en-us", usMessages);
    }

    @Override
    public Message getMessage(String locale, String messageId) {
        List<MessageGroup> messeges = contentDao.get(locale);
        if (messeges != null) {
            for (MessageGroup group : messeges) {
                for (Message msg : group.getMessages()) {
                    if (msg.getId().equalsIgnoreCase(messageId)) {
                        return msg;
                    }
                }
            }
        }
        // not found
        return null;
    }

    @Override
    public MessageGroup getMessages(String locale, String groupId) {
        List<MessageGroup> messeges = contentDao.get(locale);
        if (messeges != null) {
            for (MessageGroup messageGroup : messeges) {
                if (messageGroup.getGroup().equalsIgnoreCase(groupId)) {
                    return messageGroup;
                }
            }
        }
        // not found
        return null;
    }
}
