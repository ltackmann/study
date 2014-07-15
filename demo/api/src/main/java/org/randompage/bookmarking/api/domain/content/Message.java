package org.randompage.bookmarking.api.domain.content;

public class Message {
    private final String id;
    private final String text;

    public Message(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
