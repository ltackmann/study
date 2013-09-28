package gwtDemo.client;

import gwtDemo.client.event.LanguageChangedEvent;
import gwtDemo.client.event.LanguageChangedEventHandler;
import gwtDemo.client.view.MainView;
import gwtDemo.client.view.presenter.MainViewPresenter;
import gwtDemo.client.view.presenter.ViewPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.web.bindery.event.shared.EventBus;

public class AppController implements ValueChangeHandler<String> {
    private HasWidgets container;
    private final EventBus eventBus;

    public AppController(EventBus eventBus) {
        this.eventBus = eventBus;
        bind();
    }

    private void bind() {
        History.addValueChangeHandler(this);

        // configure operations to be carried out when events are fired
        eventBus.addHandler(LanguageChangedEvent.TYPE,
                new LanguageChangedEventHandler() {
					@Override
					public void onLanguageChanged(LanguageChangedEvent event) {
						// TODO reload current page with new language texts
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
            History.newItem("main");
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
            if (token.equals("main")) {
                presenter = new MainViewPresenter(new MainView());
            } 
        }

        if (presenter != null) {
            presenter.go(container);
        }
    }
}
