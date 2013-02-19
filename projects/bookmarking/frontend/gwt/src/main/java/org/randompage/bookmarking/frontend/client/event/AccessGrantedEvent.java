package org.randompage.bookmarking.frontend.client.event;

import com.google.gwt.event.shared.GwtEvent;
import org.randompage.bookmarking.frontend.shared.UserDTO;

public class AccessGrantedEvent extends GwtEvent<AccessGrantedEventHandler> {
    private final UserDTO user;


    public AccessGrantedEvent(UserDTO user) {
        super();
        this.user = user;
    }

    public static final Type<AccessGrantedEventHandler> TYPE = new Type<AccessGrantedEventHandler>();

    @Override
    public Type<AccessGrantedEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(AccessGrantedEventHandler handler) {
        handler.onAccess(this);
    }

    public UserDTO getUser() {
        return user;
    }
}