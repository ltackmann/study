package org.randompage.bookmarking.api.domain.content;

import java.util.LinkedList;
import java.util.List;

public class MessageGroup {
    private List<Message> messages = new LinkedList<Message>();
    private final String group;

    public MessageGroup(String group) {
        this.group = group;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getGroup() {
        return group;
    }
}
