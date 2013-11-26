package gwtDemo.client.framework;

import gwtDemo.client.event.LanguageChanged;
import gwtDemo.client.event.LanguageChangedHandler;
import gwtDemo.client.pages.MainPage;
import gwtDemo.client.pages.MainPageController;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
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
    	navigationManager.registerHandler("main", new SingletonPageRegistration<MainPageController, MainPage>(MainPage.class) {
			@Override
			public MainPageController createPageController(MainPage page, AppInjector injector) {
				return new MainPageController(page, injector);
			}

			@Override
			public MainPage createPage(AppInjector injector) {
				return new MainPage();
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

        if (token != null) {
            GWT.log("trying to lookup page for URL: " + token);
            navigationManager.showPageForUrl(token);
        }
    }
}
