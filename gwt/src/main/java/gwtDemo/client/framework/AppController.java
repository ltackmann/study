package gwtDemo.client.framework;

import gwtDemo.client.event.LanguageChanged;
import gwtDemo.client.event.LanguageChangedHandler;
import gwtDemo.client.pages.AdminPage;
import gwtDemo.client.pages.AdminPageController;
import gwtDemo.client.pages.MainPage;
import gwtDemo.client.pages.MainPageController;

import com.google.gwt.user.client.History;
import com.google.web.bindery.event.shared.EventBus;

public class AppController {
    private final EventBus eventBus;
    private final NavigationManager navigationManager;

    public AppController(AppInjector injector) {
        this.eventBus = injector.getEventBus();
        this.navigationManager = injector.getNavigationManager();
        registerEventHandlers();
        registerPages();
    }

    private void registerEventHandlers() {
        History.addValueChangeHandler(navigationManager);

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
    	
    	navigationManager.registerHandler("admin", new SingletonPageRegistration<AdminPageController, AdminPage>(AdminPage.class) {
			@Override
			public AdminPageController createPageController(AdminPage page, AppInjector injector) {
				return new AdminPageController(page, injector);
			}

			@Override
			public AdminPage createPage(AppInjector injector) {
				return new AdminPage();
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
}
