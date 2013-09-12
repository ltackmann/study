package co.tackmann.gwt.client;

import co.tackmann.gwt.client.event.AccessGrantedEvent;
import co.tackmann.gwt.client.event.AccessGrantedEventHandler;
import co.tackmann.gwt.client.view.DashboardView;
import co.tackmann.gwt.client.view.LoginView;
import co.tackmann.gwt.client.view.presenter.DashboardViewPresenter;
import co.tackmann.gwt.client.view.presenter.LoginViewPresenter;
import co.tackmann.gwt.client.view.presenter.ViewPresenter;
import co.tackmann.gwt.shared.UserDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class BookmarkingController implements ValueChangeHandler<String> {
    private HasWidgets container;
    private final HandlerManager eventBus;
    private static UserDTO sessionUser;

    public BookmarkingController(HandlerManager eventBus) {
        this.eventBus = eventBus;
        bind();
    }

    private void bind() {
        History.addValueChangeHandler(this);

        // configure operations to be carried out when events are fired
        eventBus.addHandler(AccessGrantedEvent.TYPE,
                new AccessGrantedEventHandler() {
                    @Override
                    public void onAccess(AccessGrantedEvent event) {
                        sessionUser = event.getUser();
                        History.newItem("dashboard");
                    }
                });
    }

    /**
     * Start application
     *
     * @param container
     */
    public void invoke(HasWidgets container) {
        this.container = container;

        if ("".equals(History.getToken())) {
            History.newItem("login");
        } else {
            History.fireCurrentHistoryState();
        }
    }


    /**
     * dispatch between history events (history anchor tokens) and views
     * 
     * @param event
     */
    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();
        ViewPresenter presenter = null;

        if (token != null) {
            GWT.log("handling event for token: " + token);
            if (token.equals("login")) {
                presenter = new LoginViewPresenter(new LoginView());
            } else if (token.equals("dashboard")) {
                presenter = new DashboardViewPresenter(new DashboardView(), sessionUser);
            }
        }

        if (presenter != null) {
            presenter.go(container);
        }
    }
}
