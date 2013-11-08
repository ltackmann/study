package gwtDemo.client.framework;

import gwtDemo.client.event.LanguageChanged;
import gwtDemo.client.event.LanguageChangedHandler;
import gwtDemo.client.framework.api.PageRegistration;
import gwtDemo.client.framework.api.NavigationManager;
import gwtDemo.client.framework.api.Page;
import gwtDemo.client.pages.MainPage;
import gwtDemo.client.pages.MainPageImpl;
import gwtDemo.client.pages.MainPageController;
import gwtDemo.client.view.presenter.ViewPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.web.bindery.event.shared.EventBus;

public class AppController implements ValueChangeHandler<String> {
    private final EventBus eventBus;
    private final NavigationManager navigationManager;

    public AppController(AppInjector injector) {
        this.eventBus = injector.getEventBus();
        this.navigationManager = injector.getNavigationManager();
        registerEventHandlers();
        registerPages();
    }

    private void registerEventHandlers() {
        History.addValueChangeHandler(this);

        // configure operations to be carried out when events are fired
        eventBus.addHandler(LanguageChanged.TYPE,
                new LanguageChangedHandler() {
					@Override
					public void onLanguageChanged(LanguageChanged event) {
						// TODO reload current page with new language texts
						History.newItem("dashboard");
					}
                });
    }
    
    private void registerPages() {
    	// TODO only admin can view admin-page
    	navigationManager.registerHandler("main", new PageRegistration<MainPageController, MainPage>() {
			@Override
			public MainPageController getPageController(MainPage page, AppInjector injector) {
				return new MainPageController(page, injector);
			}

			@Override
			public MainPage getPage(AppInjector injector) {
				return new MainPageImpl();
			}

			@Override
			public Class<MainPage> getPageType() {
				return MainPage.class;
			}

			@Override
			public boolean isSingleton() {
				return false;
			}
    	});
    }

    /** Start application */
    public void start() {
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
                presenter = new MainPageController(new MainPageImpl());
            } 
        }

        if (presenter != null) {
            presenter.go(container);
        }
    }
}
