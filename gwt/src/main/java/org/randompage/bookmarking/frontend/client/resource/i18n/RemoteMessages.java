package org.randompage.bookmarking.frontend.client.resource.i18n;

import com.google.gwt.http.client.RequestBuilder;

import java.util.Collection;

/**
 * Provides access to messages remote dynamically updated messages
 *
 * @author Lars Tackmann
 */
public class RemoteMessages extends JSONMessages {
    public Collection<String> bannerMessages() {
        return getMessages("banner").values();
    }
}
