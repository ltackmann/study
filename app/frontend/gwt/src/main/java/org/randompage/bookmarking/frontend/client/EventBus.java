package org.randompage.bookmarking.frontend.client;

import com.google.gwt.event.shared.HandlerManager;

public class EventBus {
    public static HandlerManager INSTANCE;
    static {
        INSTANCE = new HandlerManager(null);
    }
}
