package co.tackmann.gwt.client.resource.i18n;

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
